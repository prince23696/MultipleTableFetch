package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.CommentDto;
import com.MultipleTableFetch.Dto.CommentResponseDtoQuery;
import com.MultipleTableFetch.Dto.CommentUpdateDto;
import com.MultipleTableFetch.Entity.Comment;

public interface CommentService {
    public CommentResponseDtoQuery getAllComment();

    public Comment getComment(Long id);

    public Comment addGComment(CommentDto commentDto);

    public Comment updateComment(Long id, CommentUpdateDto commentUpdateDto);

    public Comment deleteComment(Long id);

    public CommentResponseDtoQuery findCommentByCourseId(Long courseId);
}