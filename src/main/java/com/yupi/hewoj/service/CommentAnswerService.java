package com.yupi.hewoj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.hewoj.common.PageResponse;
import com.yupi.hewoj.model.entity.CommentAnswer;

import java.util.List;

/**
* @author 31695
* @description 针对表【comment_answer(题解评论表)】的数据库操作Service
* @createDate 2024-04-28 10:43:25
*/
public interface CommentAnswerService extends IService<CommentAnswer> {

   PageResponse listConmmentByPage(long current, long size, long answerId);

}
