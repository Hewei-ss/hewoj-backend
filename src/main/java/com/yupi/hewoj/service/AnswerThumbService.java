package com.yupi.hewoj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.hewoj.model.entity.AnswerThumb;

import java.util.Set;

/**
* @author 31695
* @description 针对表【answer_thumb(题解点赞)】的数据库操作Service
* @createDate 2024-05-27 11:29:47
*/
public interface AnswerThumbService extends IService<AnswerThumb> {

    public Boolean likeAnswerOrNot(Long answerId, Long userId);
    public Boolean ifLiked(Long answerId, Long userId);

    Long getArticleLikes(Long id);
    public Set<String> getArticleLikedUsers(Long answerId);

    public void addUserToLikeSet(Long answerId, Long userId);

    public void setArticleLikes(Long articleId, Long likes);
}
