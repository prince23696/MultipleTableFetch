package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.*;
import com.MultipleTableFetch.Entity.CommentReply;
import com.MultipleTableFetch.Repository.CommentReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentReplyServiceImpl implements CommentReplyService {

    @Autowired
    CommentReplyRepository commentReplyRepository;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @Override
    public CommentReplyResponseDtoQuery getAllCommentReply() {
        int i = commentReplyRepository.countRecords();
        List<CommentReply> all = commentReplyRepository.findAll();
        CommentReplyResponseDtoQuery commentReplyResponseDtoQuery = new CommentReplyResponseDtoQuery();
        commentReplyResponseDtoQuery.setRecordCount(i);
        commentReplyResponseDtoQuery.setCommentReplyList(all);
        return commentReplyResponseDtoQuery;
    }

    @Override
    public CommentReply getCommentReply(Long id) {
        return commentReplyRepository.findById(id).get();
    }

    @Override
    public CommentReply addGCommentReply(CommentReplyDto commentReplyDto) {
        CommentReply commentReply = new CommentReply();
        commentReply.setCommentId(commentReplyDto.getCommentId());
        commentReply.setUserId(commentReplyDto.getUserId());
        commentReply.setCommentReply(commentReplyDto.getCommentReply());
        commentReply.setUsers(userService.getUserUsingId(commentReplyDto.getUserId()));
        commentReply.setComment(commentService.getComment(commentReplyDto.getCommentId()));
        return commentReplyRepository.save(commentReply);
    }

    @Override
    public CommentReply updateCommentReply(Long id, CommentReplyUpdateDto commentReplyUpdateDto) {
        return null;
    }

    @Override
    public CommentReply deleteCommentReply(Long id) {
        commentReplyRepository.deleteById(id);
        return commentReplyRepository.findById(id).get();
    }
}
