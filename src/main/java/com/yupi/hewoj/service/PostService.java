package com.yupi.hewoj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.hewoj.model.entity.Post;

import java.util.List;

/**
* @author 31695
* @description 针对表【post(帖子)】的数据库操作Service
* @createDate 2024-05-23 21:52:13
*/
public interface PostService extends IService<Post> {

    List<Post> getPostList();
}
