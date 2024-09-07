package com.yupi.hewoj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.yupi.hewoj.model.dto.user.UserQueryThumbAnswerRequest;
import com.yupi.hewoj.model.entity.AnswerFavour;

import java.util.List;

/**
* @author 31695
* @description 针对表【answer_favour(题解收藏)】的数据库操作Service
* @createDate 2024-06-06 16:51:45
*/
public interface AnswerFavourService extends IService<AnswerFavour> {

    PageInfo<AnswerFavour> getthumbanswerlistPage(UserQueryThumbAnswerRequest userQueryThumbAnswerRequest);
}
