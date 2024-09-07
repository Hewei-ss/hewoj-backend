package com.yupi.hewoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yupi.hewoj.mapper.CommentAnswerMapper;
import com.yupi.hewoj.model.entity.CommentAnswer;
import com.yupi.hewoj.model.entity.CommentReply;
import com.yupi.hewoj.service.CommentAnswerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 31695
 * @description 针对表【comment_answer(题解评论表)】的数据库操作Service实现
 * @createDate 2024-04-28 10:43:25
 */
@Service
public class CommentAnswerServiceImpl extends ServiceImpl<CommentAnswerMapper, CommentAnswer>
        implements CommentAnswerService {

    @Resource
    private CommentAnswerMapper commentAnswerMapper;


    @Override
    public PageInfo<CommentAnswer> listConmmentByPage(int current, int size, long answerId ) {
        PageHelper.startPage(current, size);
        List<CommentAnswer> list = commentAnswerMapper.listConmmentByPage(answerId);
        return new PageInfo<CommentAnswer>(list);

    }


}




