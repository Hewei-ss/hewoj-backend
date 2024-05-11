package com.yupi.hewoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.hewoj.model.entity.CommentReply;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 31695
* @description 针对表【comment_reply(对评论的回复表)】的数据库操作Mapper
* @createDate 2024-05-08 15:20:53
* @Entity generator.domain.CommentReply
*/
public interface CommentReplyMapper extends BaseMapper<CommentReply> {
    List<CommentReply> replyCommentList(@Param("targetCommentId") long targetCommentId);

}




