package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.UploadQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<UploadQuiz, Long> {
}
