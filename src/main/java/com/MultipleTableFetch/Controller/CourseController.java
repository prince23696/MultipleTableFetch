package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Dto.CourseDetailsResponseDto;
import com.MultipleTableFetch.Dto.CourseDto;
import com.MultipleTableFetch.Dto.CourseResponseDtoQuery;
import com.MultipleTableFetch.Dto.ResponseHandler;
import com.MultipleTableFetch.Entity.Course;
import com.MultipleTableFetch.Entity.CourseEnum;
import com.MultipleTableFetch.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("getCourse")
    public ResponseEntity<Object> getCourse(@RequestParam Long id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        Course course = courseService.getCourse(id);
        return ResponseHandler.response(course, "Successfully Getting Course.", true, HttpStatus.OK);
    }

    @GetMapping("/getAllCourse")
    public ResponseEntity<Object> getAllCourse(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        CourseDetailsResponseDto allCourse = courseService.getAllCourse();
        return ResponseHandler.response(allCourse, "Successfully Getting All Course.", true, HttpStatus.OK);

    }

    @PostMapping("/saveCourse")
    public ResponseEntity<Object> addCourse(@RequestBody CourseDto courseDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        Course course1 = courseService.addCourse(courseDto);
        return ResponseHandler.response(course1, "Successfully Created Course.", true, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCourse")
    public String deleteGender(@RequestParam Long id) {
        courseService.deleteCourse(id);
        return "Gender deleted form database id-" + id;
    }

    @PutMapping("/updateCourse")
    public ResponseEntity<Object> updateCourse(@RequestParam Long id, @RequestBody Course course) {
        Course course1 = courseService.updateCourse(id, course);
        return ResponseHandler.response(course1, "Successfully updated Course.", true, HttpStatus.OK);
    }

    @GetMapping("findByCourseDetailsByUserIdAndCategoryId")
    public ResponseEntity<Object> findByCourseDetailsByUserIdAndCategoryId(@RequestParam(required = false) Integer userId, @RequestParam Integer categoryId, @RequestParam(defaultValue = "0") Integer offset, @RequestParam(required = false) Integer pageSize, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        CourseResponseDtoQuery byCourseDetailsByUserIdAndCategoryId = courseService.findByCourseDetailsByUserIdAndCategoryId(userId, categoryId, offset, pageSize);
        if (byCourseDetailsByUserIdAndCategoryId != null)
            return ResponseHandler.response(byCourseDetailsByUserIdAndCategoryId, "Successfully Getting Course.", true, HttpStatus.OK);
        else
            return ResponseHandler.response("", "Invalid... Operation Failure.", false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("findByCourseDetailsByCourseStatus")
    public ResponseEntity<Object> findByCourseDetailsByCourseStatus(@RequestParam CourseEnum status, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        String s = String.valueOf(status);
        CourseResponseDtoQuery byCourseDetailsByCourseStatus = courseService.findByCourseDetailsByCourseStatus(s);
        if (byCourseDetailsByCourseStatus != null)
            return ResponseHandler.response(byCourseDetailsByCourseStatus, "Successfully Getting Course.", true, HttpStatus.OK);
        else
            return ResponseHandler.response("", "Invalid... Operation Failure.", false, HttpStatus.BAD_REQUEST);
    }

}