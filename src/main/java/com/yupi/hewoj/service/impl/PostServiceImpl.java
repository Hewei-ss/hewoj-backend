package com.yupi.hewoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.hewoj.mapper.PostMapper;
import com.yupi.hewoj.model.entity.Post;
import com.yupi.hewoj.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 31695
* @description 针对表【post(帖子)】的数据库操作Service实现
* @createDate 2024-05-23 21:52:13
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService {

    @Resource
    private PostMapper postMapper;

    @Override
    public List<Post> getPostList() {
      return postMapper.getPostList();
    }
}




