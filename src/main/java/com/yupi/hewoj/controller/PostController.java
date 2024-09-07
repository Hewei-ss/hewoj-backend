package com.yupi.hewoj.controller;

import com.google.gson.Gson;
import com.yupi.hewoj.common.BaseResponse;
import com.yupi.hewoj.common.ResultUtils;
import com.yupi.hewoj.exception.BusinessException;
import com.yupi.hewoj.model.dto.post.PostAddRequest;
import com.yupi.hewoj.model.entity.Post;
import com.yupi.hewoj.model.enums.ResponseCodeEnum;
import com.yupi.hewoj.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 帖子服务
 */

@RestController
@RequestMapping("/post")
public class PostController {
    @Resource
    private PostService postService;
    @Resource
    private Gson GSON;
//    private final static Gson GSON = new Gson();

    @PostMapping("/add")
    public BaseResponse<Boolean> addPost(@RequestBody PostAddRequest postAddRequest) {
        if (postAddRequest == null) {
            throw new BusinessException(ResponseCodeEnum.PARAMS_ERROR);
        }
        Post post = new Post();
        BeanUtils.copyProperties(postAddRequest, post);
        List<String> imageFileNamelist = postAddRequest.getImages();
        if (imageFileNamelist != null) {
            post.setImages(GSON.toJson(imageFileNamelist));
        }
        postService.save(post);
        return ResultUtils.success(true);
    }

    /**
     * 获取帖子列表
     * @return
     */
    @GetMapping("/get/list")
    public BaseResponse<List<Post>> getPostList() {
        List<Post> postList = postService.getPostList();
        return ResultUtils.success(postList);
    }



}
