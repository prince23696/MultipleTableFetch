package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Dto.CourseDetailsDto;
import com.MultipleTableFetch.Entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "select count(course_id) from course ", nativeQuery = true)
    public int countRecords();

    @Query(value = "select count(course_id) from course c where c.category_id=?1  ", nativeQuery = true)
    public int countRecordsByCategoryId(int categoryId);

    @Query(value = "select c.course_id as courseId,c.user_id as userId,c.course_name as courseName,c.course_sub_name as courseSubName,c.end_date as endDate,c.start_date as startDate," +
            "c.category_id as categoryId ,c.sub_category_id as subCategoryId,c.subject_id as subjectId,c.description as description,c.currency_type as currencyType," +
            "c.price as price,c.status as status from course c", nativeQuery = true)
    public List<CourseDetailsDto> findByCourseDetailsDto();

    @Query(value = "select * from course c where c.category_id=?1  ", nativeQuery = true)
    public Page<Course> findByCourseDetailsByCategoryId(int id, Pageable pageable);

    @Query(value = "select * from course c where c.status=?1  ", nativeQuery = true)
    public List<Course> findByCourseDetailsByCourseStatus(String status);

    @Query(value = "select * from course c where c.user_id=?1 and c.category_id=?2", nativeQuery = true)
    public Page<Course> findByCourseDetailsByUserIdAndCategoryId(int userId, int categoryId, Pageable pageable);
}