package com.yupi.hewoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yupi.hewoj.mapper.CommentPostMapper;
import com.yupi.hewoj.model.entity.CommentAnswer;
import com.yupi.hewoj.model.entity.CommentPost;
import com.yupi.hewoj.service.CommentPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 31695
* @description 针对表【comment_post(贴子主评论表)】的数据库操作Service实现
* @createDate 2024-06-07 03:31:45
*/
@Service
public class CommentPostServiceImpl extends ServiceImpl<CommentPostMapper, CommentPost>
    implements CommentPostService {

    @Resource
    private CommentPostMapper commentPostMapper;

    @Override
    public PageInfo<CommentPost> listConmmentPostByPage(int current, int size, long postId) {
        PageHelper.startPage(current, size);
        List<CommentPost> list = commentPostMapper.listConmmentPostByPage(postId);
        return new PageInfo<CommentPost>(list);
    }
}




