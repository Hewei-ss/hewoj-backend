package com.yupi.hewoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.hewoj.mapper.CommentPostReplyMapper;
import com.yupi.hewoj.model.entity.CommentPostReply;
import com.yupi.hewoj.service.CommentPostReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 31695
* @description 针对表【comment_post_reply(对贴子评论的回复表)】的数据库操作Service实现
* @createDate 2024-06-07 05:02:41
*/
@Service
public class CommentPostReplyServiceImpl extends ServiceImpl<CommentPostReplyMapper, CommentPostReply>
    implements CommentPostReplyService {


    @Resource
    private CommentPostReplyMapper commentPostReplyMapper;
    @Override
    public List<CommentPostReply> postReplyCommentList(long postReplyCommentId) {
        return commentPostReplyMapper.postReplyCommentList(postReplyCommentId);
    }
}




