package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select count(comment_id) from comment ", nativeQuery = true)
    public int countRecords();

    @Query(value = "select * from comment where course_id=?1 ", nativeQuery = true)
    public List<Comment> findCommentByCourseId(Long id);

}