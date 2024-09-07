package com.yupi.hewoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.hewoj.model.entity.Post;

import java.util.List;

/**
* @author 31695
* @description 针对表【post(帖子)】的数据库操作Mapper
* @createDate 2024-05-23 21:52:13
* @Entity generator.domain.Post
*/
public interface PostMapper extends BaseMapper<Post> {

    List<Post> getPostList();
}




