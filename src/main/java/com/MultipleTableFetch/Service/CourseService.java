package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.CourseDetailsResponseDto;
import com.MultipleTableFetch.Dto.CourseDto;
import com.MultipleTableFetch.Dto.CourseResponseDtoQuery;
import com.MultipleTableFetch.Entity.Course;
import com.MultipleTableFetch.Entity.CourseRating;

public interface CourseService {

    public CourseDetailsResponseDto getAllCourse();

    public Course getCourse(Long id);

    public Course addCourse(CourseDto courseDto);

    public Course updateCourse(Long id, Course course);

    public Course deleteCourse(Long id);

    public CourseResponseDtoQuery findByCourseDetailsByUserIdAndCategoryId(Integer userId, Integer categoryId, Integer offset, Integer pageSize);

    public CourseResponseDtoQuery findByCourseDetailsByCourseStatus(String status);

    public CourseRating addCourseRating(CourseRating courseRating);
}