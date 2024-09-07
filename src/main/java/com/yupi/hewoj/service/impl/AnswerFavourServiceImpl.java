package com.yupi.hewoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yupi.hewoj.mapper.AnswerFavourMapper;
import com.yupi.hewoj.model.dto.user.UserQueryThumbAnswerRequest;
import com.yupi.hewoj.model.entity.AnswerFavour;
import com.yupi.hewoj.model.entity.CommentAnswer;
import com.yupi.hewoj.service.AnswerFavourService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 31695
* @description 针对表【answer_favour(题解收藏)】的数据库操作Service实现
* @createDate 2024-06-06 16:51:45
*/
@Service
public class AnswerFavourServiceImpl extends ServiceImpl<AnswerFavourMapper, AnswerFavour>
    implements AnswerFavourService {

    @Resource
    private AnswerFavourMapper answerFavourMapper;

    @Override
    public PageInfo<AnswerFavour> getthumbanswerlistPage(UserQueryThumbAnswerRequest userQueryThumbAnswerRequest) {
        int current=userQueryThumbAnswerRequest.getCurrent();
        int size=userQueryThumbAnswerRequest.getPageSize();
        PageHelper.startPage(current, size);
        List<AnswerFavour> list = answerFavourMapper.getthumbanswerlistPage(userQueryThumbAnswerRequest.getUserId());
        return new PageInfo<AnswerFavour>(list);


    }
}




