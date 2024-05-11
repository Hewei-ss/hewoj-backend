package com.yupi.hewoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.hewoj.mapper.CommentReplyMapper;
import com.yupi.hewoj.model.entity.CommentReply;
import com.yupi.hewoj.service.CommentReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 31695
* @description 针对表【comment_reply(对评论的回复表)】的数据库操作Service实现
* @createDate 2024-05-08 15:20:53
*/
@Service
public class CommentReplyServiceImpl extends ServiceImpl<CommentReplyMapper, CommentReply>
    implements CommentReplyService {

    @Resource
    private CommentReplyMapper commentReplyMapper;

    @Override
    public List<CommentReply> replyCommentList(long targetCommentId) {
        return commentReplyMapper.replyCommentList(targetCommentId);
    }
}




