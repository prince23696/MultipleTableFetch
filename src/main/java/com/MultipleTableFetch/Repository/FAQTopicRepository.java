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

    @Query(value = "select * from faqtopic f where f.faq_topic_id=?1", nativeQuery = true)
    public List<FAQTopic> findByFAQTopicDetailsById(int id);

    @Query(value = "select * from faqtopic f where f.faq_topic_name like %:name% ", nativeQuery = true)
    public List<FAQTopic> findByFAQTopicDetailsByName(String name);

    @Query(value = "select * from faqtopic f where f.faq_topic_id=:id or f.faq_topic_name like %:name%", nativeQuery = true)
    public List<FAQTopic> findByFAQTopicDetailsByNameAndId(int id, String name);


    @Query(value = "SELECT EXISTS(select * from faqtopic f where f.faq_topic_name =?1)", nativeQuery = true)
    public Boolean findByFAQTopicByTopicName(String name);
}