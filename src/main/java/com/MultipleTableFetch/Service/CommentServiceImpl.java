package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.CommentDto;
import com.MultipleTableFetch.Dto.CommentResponseDtoQuery;
import com.MultipleTableFetch.Dto.CommentUpdateDto;
import com.MultipleTableFetch.Entity.Comment;
import com.MultipleTableFetch.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    @Autowired
    CommentRepository commentRepository;

    @Override
    public CommentResponseDtoQuery getAllComment() {
        int i = commentRepository.countRecords();
        List<Comment> all = commentRepository.findAll();
        CommentResponseDtoQuery commentResponseDtoQuery = new CommentResponseDtoQuery();
        commentResponseDtoQuery.setRecordCount(i);
        commentResponseDtoQuery.setCommentList(all);
        return commentResponseDtoQuery;
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public Comment addGComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setCourseId(commentDto.getCourseId());
        comment.setUserId(commentDto.getUserId());
        comment.setComment(commentDto.getComment());
        comment.setCourse(courseService.getCourse(commentDto.getCourseId()));
        comment.setUsers(userService.getUserUsingId(commentDto.getUserId()));
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long id, CommentUpdateDto commentUpdateDto) {
        Comment comment1 = commentRepository.findById(id).get();
        comment1.setUserId(commentUpdateDto.getUserId());
        comment1.setCourseId(commentUpdateDto.getCourseId());
        comment1.setComment(commentUpdateDto.getComment());
        comment1.setCourse(courseService.getCourse(commentUpdateDto.getCourseId()));
        comment1.setUsers(userService.getUserUsingId(commentUpdateDto.getUserId()));
        commentRepository.save(comment1);
        return comment1;
    }

    @Override
    public Comment deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).get();
        commentRepository.deleteById(id);
        return comment;
    }

    @Override
    public CommentResponseDtoQuery findCommentByCourseId(Long courseId) {
        int i = commentRepository.countRecords();
        List<Comment> comment = commentRepository.findCommentByCourseId(courseId);
        CommentResponseDtoQuery commentResponseDtoQuery = new CommentResponseDtoQuery();
        commentResponseDtoQuery.setRecordCount(i);
        commentResponseDtoQuery.setCommentList(comment);
        return commentResponseDtoQuery;
    }
}
