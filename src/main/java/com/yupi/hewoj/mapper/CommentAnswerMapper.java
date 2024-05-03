package com.yupi.hewoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.hewoj.common.PageResponse;
import com.yupi.hewoj.model.entity.CommentAnswer;

import java.util.List;

/**
* @author 31695
* @description 针对表【comment_answer(题解评论表)】的数据库操作Mapper
* @createDate 2024-04-28 10:43:25
* @Entity generator.domain.CommentAnswer
*/
public interface CommentAnswerMapper extends BaseMapper<CommentAnswer> {
    PageResponse listConmmentByPage(long id);

}




