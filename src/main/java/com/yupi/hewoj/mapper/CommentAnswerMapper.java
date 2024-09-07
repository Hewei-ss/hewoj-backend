package com.yupi.hewoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.hewoj.model.entity.CommentAnswer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 31695
* @description 针对表【comment_answer(题解评论表)】的数据库操作Mapper
* @createDate 2024-04-28 10:43:25
* @Entity generator.domain.CommentAnswer
*/
public interface CommentAnswerMapper extends BaseMapper<CommentAnswer> {
    List<CommentAnswer> listConmmentByPage(@Param("answerId") long id);
    List<CommentAnswer> listFuConmment(@Param("answerId") long id,@Param("replyUserId") long replyUserId);
}




