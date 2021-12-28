package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.CommentReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReplyRepository extends JpaRepository<CommentReply, Long> {

    @Query(value = "select count(comment_reply_id) from comment_reply ", nativeQuery = true)
    public int countRecords();

}