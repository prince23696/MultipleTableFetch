package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.*;
import com.MultipleTableFetch.Entity.CommentReply;

public interface CommentReplyService {
    public CommentReplyResponseDtoQuery getAllCommentReply();

    public CommentReply getCommentReply(Long id);

    public CommentReply addGCommentReply(CommentReplyDto commentReplyDto);

    public CommentReply updateCommentReply(Long id, CommentReplyUpdateDto commentReplyUpdateDto);

    public CommentReply deleteCommentReply(Long id);
}