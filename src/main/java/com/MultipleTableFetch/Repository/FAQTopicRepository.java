package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.FAQTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FAQTopicRepository extends JpaRepository<FAQTopic, Integer> {

    @Query(value = "select count(faq_topic_id) from faqtopic ", nativeQuery = true)
    public int countRecords();

    @Query(value = "select * from faqtopic f where faq_category_id=?1 ", nativeQuery = true)
    public List<FAQTopic> findByFAQTopicDetailsDto(int categoryId);

    @Query(value = "SELECT EXISTS(select * from faqtopic f where f.faq_topic_name =?1)", nativeQuery = true)
    public Boolean findByFAQTopicByTopicName(String name);
}