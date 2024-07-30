package com.yupi.hewoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.hewoj.constant.RedisContant;
import com.yupi.hewoj.mapper.AnswerThumbMapper;
import com.yupi.hewoj.model.entity.AnswerThumb;
import com.yupi.hewoj.service.AnswerThumbService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
* @author 31695
* @description 针对表【answer_thumb(题解点赞)】的数据库操作Service实现
* @createDate 2024-05-27 11:29:47
*/
@Service
public class AnswerThumbServiceImpl extends ServiceImpl<AnswerThumbMapper, AnswerThumb> implements AnswerThumbService
{

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    // 添加用户到文章的点赞集合中，同时设置集合键永不过期
    public void addUserToLikeSet(Long answerId, Long userId)
    {
        Long add = stringRedisTemplate.opsForSet().add(RedisContant.ANSWER_LIKE_USERS_KEY+answerId, userId.toString());
        // 设置集合键150年过期
        stringRedisTemplate.expire(RedisContant.ANSWER_LIKE_USERS_KEY+answerId, 365 * 150, TimeUnit.DAYS);
    }


    // 检查用户是否已经点赞
    public boolean isUserLiked(Long answerId, Long userId)
    {
        return Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember(RedisContant.ANSWER_LIKE_USERS_KEY+answerId, userId.toString()));
    }

    // 设置文章的点赞数
    public void setArticleLikes(Long articleId, Long likes)
    {
        stringRedisTemplate.opsForHash().put(RedisContant.ANSWER_LIKE_COUNT, articleId.toString(), likes.toString());
    }

    // 获取文章的点赞数
    public Long getArticleLikes(Long articleId)
    {
        Object likes = stringRedisTemplate.opsForHash().get(RedisContant.ANSWER_LIKE_COUNT, articleId.toString());
        return likes != null ? Long.parseLong(likes.toString()) : 0L;
    }

    // 获取文章点赞的用户ID集合
    public Set<String> getArticleLikedUsers(Long answerId)
    {
        Set<String> likedUsers = stringRedisTemplate.opsForSet().members(RedisContant.ANSWER_LIKE_USERS_KEY+answerId);
        return likedUsers != null ? likedUsers : Collections.emptySet();
    }


    // 移除用户从文章的点赞集合中
    public void removeUserFromLikeSet(Long answerId, Long userId)
    {
        stringRedisTemplate.opsForSet().remove(RedisContant.ANSWER_LIKE_USERS_KEY+answerId, userId.toString());
    }

    // 点赞
    public void like(Long articleId, Long userId)
    {
        addUserToLikeSet(articleId, userId);
        Long likes = getArticleLikes(articleId);
        if (likes >= 0)
        {
            setArticleLikes(articleId, likes + 1);
        }
    }

    // 取消点赞
    public void cancelLike(Long articleId, Long userId)
    {
        if (isUserLiked(articleId, userId))
        {
            removeUserFromLikeSet(articleId, userId);
            Long likes = getArticleLikes(articleId);
            if (likes > 0)
            {
                setArticleLikes(articleId, likes - 1);
            }
        }
    }

    @Override
    public Boolean likeAnswerOrNot(Long articleId, Long userId)
    {
        // 获得当前点赞文章的用户集合
        Set<String> likeUsers = getArticleLikedUsers(articleId);
        // 如果存在该用户，就取消点赞
        if (likeUsers.size() > 0 && likeUsers.contains(userId.toString()))
        {
            cancelLike(articleId, userId);
            return false;
        }
        // 反之，点赞
        else
        {
            like(articleId, userId);
            return true;
        }
    }


    @Override
    public Boolean ifLiked(Long articleId, Long userId)
    {
        // 首先从redis检查，如果有，那么数据库里面最终也一定会有
        Set<String> articleLikedUsers = getArticleLikedUsers(articleId).stream().map(e -> (String) e).collect(Collectors.toSet());
        if (articleLikedUsers.contains(userId.toString())) return true;
        /*
        这块感觉不用，保证实时性比较好，redis宕机后再同步就好了
        // 如果redis中没有，则从数据库中查，有则有，否则那确实是没有
        QueryWrapper<ArticleLikes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        queryWrapper.eq("user_id", userId);
        List<ArticleLikes> list = this.list(queryWrapper);
        return !list.isEmpty();
         */
        return false;
    }
}


