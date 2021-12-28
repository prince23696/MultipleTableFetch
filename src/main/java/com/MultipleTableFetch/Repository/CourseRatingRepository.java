package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.CourseRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRatingRepository extends JpaRepository<CourseRating, Long> {

    @Query(value = "select count(course_rating_id) from course_rating ", nativeQuery = true)
    public int countRecords();

    @Query(value = "select AVG(rating) from course_rating where course_id=?1", nativeQuery = true)
    public float findAverageByCourseId(Long courseId);

    @Query(value = "select AVG(rating) from course_rating where course_id=?1 and user_id=?2", nativeQuery = true)
    public float UserCourseRating(Long courseId, Integer userId);
}