package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.FAQQuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FAQQuestionAnswerRepository extends JpaRepository<FAQQuestionAnswer, Integer> {
    @Query(value = "select count(faq_question_id) from faqquestion_answer", nativeQuery = true)
    public int countRecords();

    @Query(value = "select * from faqquestion_answer f where faq_topic_id=?1 ", nativeQuery = true)
    public List<FAQQuestionAnswer> findByFAQQuestionAnswerDetailsDto(int categoryId);

    @Query(value = "SELECT EXISTS(select * from faqquestion_answer f where f.faq_question =?1)", nativeQuery = true)
    public Boolean findByFAQQuestionByQuestion(String name);
}