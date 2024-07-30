package com.yupi.hewoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.hewoj.model.entity.CommentPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 31695
* @description 针对表【comment_post(贴子主评论表)】的数据库操作Mapper
* @createDate 2024-06-07 03:31:45
* @Entity generator.domain.CommentPost
*/
public interface CommentPostMapper extends BaseMapper<CommentPost> {

    List<CommentPost> listConmmentPostByPage(@Param("postId") long postId);
}




