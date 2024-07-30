package com.yupi.hewoj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.yupi.hewoj.model.entity.CommentAnswer;
import com.yupi.hewoj.model.entity.CommentPost;

/**
* @author 31695
* @description 针对表【comment_post(贴子主评论表)】的数据库操作Service
* @createDate 2024-06-07 03:31:45
*/
public interface CommentPostService extends IService<CommentPost> {
    PageInfo<CommentPost> listConmmentPostByPage(int current, int size, long postId);

}
