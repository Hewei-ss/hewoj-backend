package com.yupi.hewoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.hewoj.model.entity.CommentPostReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 31695
 * @description 针对表【comment_post_reply(对贴子评论的回复表)】的数据库操作Mapper
 * @createDate 2024-06-07 05:02:41
 * @Entity generator.domain.CommentPostReply
 */
public interface CommentPostReplyMapper extends BaseMapper<CommentPostReply> {

    List<CommentPostReply> postReplyCommentList(@Param("postReplyCommentId") long postReplyCommentId);
}




