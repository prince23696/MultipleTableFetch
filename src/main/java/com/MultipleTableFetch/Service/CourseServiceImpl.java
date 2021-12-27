package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.CourseDetailsDto;
import com.MultipleTableFetch.Dto.CourseDetailsResponseDto;
import com.MultipleTableFetch.Dto.CourseDto;
import com.MultipleTableFetch.Dto.CourseResponseDtoQuery;
import com.MultipleTableFetch.Entity.Course;
import com.MultipleTableFetch.Helper.Pair;
import com.MultipleTableFetch.Helper.Utils;
import com.MultipleTableFetch.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SubCategoryService subCategoryService;
    @Autowired
    SubjectService subjectService;

    @Override
    public CourseDetailsResponseDto getAllCourse() {
        //return courseRepository.findAll();
        int i = courseRepository.countRecords();
        List<CourseDetailsDto> byCourseDetailsDto = courseRepository.findByCourseDetailsDto();
        CourseDetailsResponseDto courseDetailsResponseDto = new CourseDetailsResponseDto();
        courseDetailsResponseDto.setRecordCount(i);
        courseDetailsResponseDto.setCourseDetailsDtoList(byCourseDetailsDto);
        return courseDetailsResponseDto;
    }

    @Override
    public Course getCourse(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public Course addCourse(CourseDto course) {
        Course course1 = new Course();
        course1.setUserId(course.getUserId());
        course1.setCourseName(course.getCourseName());
        course1.setCourseSubName(course.getCourseSubName());
        course1.setEndDate(course.getEndDate());
        course1.setStartDate(course.getStartDate());
        course1.setCategoryId(course.getCategoryId());
        course1.setSubCategoryId(course.getSubCategoryId());
        course1.setSubjectId(course.getSubjectId());
        course1.setDescription(course.getDescription());
        course1.setCurrencyType(course.getCurrencyType());
        course1.setPrice(course.getPrice());
        course1.setStatus(course.getStatus());
        course1.setUsers(userService.getUserUsingId(course1.getUserId()));
        course1.setCategory(categoryService.getCategory(course.getCategoryId()));
        course1.setSubCategory(subCategoryService.getSubCategory(course.getSubCategoryId()));
        course1.setSubject(subjectService.getSubject(course.getSubjectId()));
        courseRepository.save(course1);
        return course1;
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course course1 = courseRepository.findById(id).get();
        course1.setUserId(course.getUserId());
        course1.setCourseName(course.getCourseName());
        course1.setCourseSubName(course.getCourseSubName());
        course1.setEndDate(course.getEndDate());
        course1.setStartDate(course.getStartDate());
        course1.setCategoryId(course.getCategoryId());
        course1.setSubCategoryId(course.getSubCategoryId());
        course1.setSubjectId(course.getSubjectId());
        course1.setDescription(course.getDescription());
        course1.setCurrencyType(course.getCurrencyType());
        course1.setPrice(course.getPrice());
        course1.setStatus(course.getStatus());
        course1.setUsers(userService.getUserUsingId(course.getUserId()));
        course1.setCategory(categoryService.getCategory(course.getCategoryId()));
        course1.setSubCategory(subCategoryService.getSubCategory(course.getSubCategoryId()));
        course1.setSubject(subjectService.getSubject(course.getSubjectId()));
        courseRepository.save(course1);
        return course1;
    }

    @Override
    public Course deleteCourse(Long id) {
        Course course1 = courseRepository.findById(id).get();
        courseRepository.deleteById(id);
        return course1;
    }

    @Override
    public CourseResponseDtoQuery findByCourseDetailsByUserIdAndCategoryId(Integer userId, Integer categoryId, Integer offset, Integer pageSize) {

        if (userId == null && categoryId != null) {
            Pair pair = Utils.fetchCounts(pageSize);
            //  Pageable pageable = PageRequest.of(pageSize, pair.getFetchCount());
            Pageable pageable = PageRequest.of(offset, pageSize);
            int i = courseRepository.countRecordsByCategoryId(categoryId);
            CourseResponseDtoQuery courseResponseDtoQuery = new CourseResponseDtoQuery();
            courseResponseDtoQuery.setRecordCount(i);
            Page<Course> byCourseDetailsByCategoryId = courseRepository.findByCourseDetailsByCategoryId(categoryId, pageable);
            courseResponseDtoQuery.setCourseList(byCourseDetailsByCategoryId);
            return courseResponseDtoQuery;
        } else if (userId != null && categoryId != null) {
            Pageable pageable = PageRequest.of(offset, pageSize);
            int i = courseRepository.countRecordsByCategoryId(categoryId);
            CourseResponseDtoQuery courseResponseDtoQuery = new CourseResponseDtoQuery();
            courseResponseDtoQuery.setRecordCount(i);
            Page<Course> byCourseDetailsByUserIdAndCategoryId = courseRepository.findByCourseDetailsByUserIdAndCategoryId(userId, categoryId, pageable);
            courseResponseDtoQuery.setCourseList(byCourseDetailsByUserIdAndCategoryId);
            return courseResponseDtoQuery;
        } else return null;
    }

    @Override
    public CourseResponseDtoQuery findByCourseDetailsByCourseStatus(String status) {
        int i = courseRepository.countRecords();
        List<Course> byCourseDetailsByCourseStatus = courseRepository.findByCourseDetailsByCourseStatus(status);
        CourseResponseDtoQuery courseResponseDtoQuery = new CourseResponseDtoQuery();
        courseResponseDtoQuery.setRecordCount(i);
        courseResponseDtoQuery.setCourseStatusDtoList(byCourseDetailsByCourseStatus);
        return courseResponseDtoQuery;
    }
}