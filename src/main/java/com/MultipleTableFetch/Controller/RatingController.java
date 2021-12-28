package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Dto.*;
import com.MultipleTableFetch.Entity.Comment;
import com.MultipleTableFetch.Entity.CommentReply;
import com.MultipleTableFetch.Entity.CourseRating;
import com.MultipleTableFetch.Entity.GuideRating;
import com.MultipleTableFetch.Repository.CourseRatingRepository;
import com.MultipleTableFetch.Service.CommentReplyService;
import com.MultipleTableFetch.Service.CommentService;
import com.MultipleTableFetch.Service.CourseService;
import com.MultipleTableFetch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class RatingController {

    @Autowired
    CommentService commentService;
    @Autowired
    CommentReplyService commentReplyService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseRatingRepository courseRatingRepository;
    @Autowired
    UserService userService;

    @GetMapping("getComment")
    public ResponseEntity<Object> getComment(@RequestParam Long id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        Comment course = commentService.getComment(id);
        return ResponseHandler.response(course, "Successfully Getting Comment.", true, HttpStatus.OK);
    }

    @GetMapping("/getAllComment")
    public ResponseEntity<Object> getAllComment(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        CommentResponseDtoQuery allCourse = commentService.getAllComment();
        return ResponseHandler.response(allCourse, "Successfully Getting All Comment.", true, HttpStatus.OK);

    }

    @PostMapping("/postComment")
    public ResponseEntity<Object> addComment(@RequestBody CommentDto commentDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        Comment comment1 = commentService.addGComment(commentDto);
        return ResponseHandler.response(comment1, "Successfully Created Comment.", true, HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment")
    public String deleteComment(@RequestParam Long id) {
        commentService.deleteComment(id);
        return "Comment deleted form database id-" + id;
    }

    @PutMapping("/updateComment")
    public ResponseEntity<Object> updateComment(@RequestParam Long id, @RequestBody CommentUpdateDto commentUpdateDto) {
        Comment comment1 = commentService.updateComment(id, commentUpdateDto);
        return ResponseHandler.response(comment1, "Successfully updated Comment.", true, HttpStatus.OK);
    }

    @PostMapping("commentReply")
    public ResponseEntity<Object> commentReply(@RequestBody CommentReplyDto commentReplyDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        CommentReply commentReply1 = commentReplyService.addGCommentReply(commentReplyDto);
        return ResponseHandler.response(commentReply1, "Successfully Posted Reply On Comment.", true, HttpStatus.OK);
    }

    @GetMapping("commentByCourseId")
    public ResponseEntity<Object> commentByCourseId(@RequestParam Long courseId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        CommentResponseDtoQuery comment = commentService.findCommentByCourseId(courseId);
        return ResponseHandler.response(comment, "Successfully Get Course By CourseId.", true, HttpStatus.OK);
    }

    @PostMapping("addCourseRating")
    public ResponseEntity<Object> CourseRating(@RequestBody CourseRating courseRating, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        CourseRating courseRating1 = courseService.addCourseRating(courseRating);
        return ResponseHandler.response(courseRating1, "Successfully Posted Rating On Course.", true, HttpStatus.OK);
    }

    @GetMapping("getCourseRatingByCourseId")
    public ResponseEntity<Object> getCourseRatingByCourseId(@RequestParam Long courseId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        float averageByCourseId = courseRatingRepository.findAverageByCourseId(courseId);
        return ResponseHandler.response(averageByCourseId, "Successfully Get Average Course Rating By CourseId.", true, HttpStatus.OK);
    }

    @GetMapping("getUserCourseRating")
    public ResponseEntity<Object> getUserCourseRating(@RequestParam Integer userId, @RequestParam Long courseId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        float averageByCourseId = courseRatingRepository.UserCourseRating(courseId, userId);
        return ResponseHandler.response(averageByCourseId, "Successfully Get Average Course Rating By CourseId and UserId.", true, HttpStatus.OK);
    }

    @PostMapping("postGuideRating")
    public ResponseEntity<Object> postGuideRating(@RequestBody GuideRating guideRating, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        GuideRating guideRating1 = userService.addGuideRating(guideRating);
        return ResponseHandler.response(guideRating1, "Successfully Posted Guide Rating.", true, HttpStatus.OK);
    }

    @GetMapping("getAllGuideRating")
    public ResponseEntity<Object> getAllGuideRating(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        List<GuideRating> allGuide = userService.getAllGuide();
        return ResponseHandler.response(allGuide, "Successfully Get All Guide Rating.", true, HttpStatus.OK);
    }

    @GetMapping("getGuideRating")
    public ResponseEntity<Object> getGuideRating(@RequestParam Long guideId, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        GuideRating guide = userService.getGuideRating(guideId);
        return ResponseHandler.response(guide, "Successfully Get AGuide Rating.", true, HttpStatus.OK);
    }
}