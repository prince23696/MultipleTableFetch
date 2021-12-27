package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.FAQTopicUpdateDto;
import com.MultipleTableFetch.Entity.FAQTopic;

import java.util.List;

public interface FAQTopicService {

    public List<FAQTopic> getAllFAQTopic(int categoryId);

    public FAQTopic getFAQTopic(int id);

    public FAQTopic addFAQTopic(FAQTopic faqTopic);

    public FAQTopic updateFAQTopic(int id, FAQTopicUpdateDto faqTopicUpdateDto);

    public FAQTopic deleteFAQTopic(int id);

    public List<FAQTopic> getFAQTopicByNameOrId(Integer id, String name);
}