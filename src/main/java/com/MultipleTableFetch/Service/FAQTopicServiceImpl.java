package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.FAQTopicUpdateDto;
import com.MultipleTableFetch.Entity.FAQTopic;
import com.MultipleTableFetch.Repository.FAQTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQTopicServiceImpl implements FAQTopicService {

    @Autowired
    FAQTopicRepository faqTopicRepository;
    @Autowired
    FAQCategoryService faqCategoryService;

    @Override
    public List<FAQTopic> getAllFAQTopic(int categoryId) {
        return faqTopicRepository.findByFAQTopicDetailsDto(categoryId);
    }

    @Override
    public FAQTopic getFAQTopic(int id) {
        return faqTopicRepository.findById(id).get();
    }

    @Override
    public FAQTopic addFAQTopic(FAQTopic faqTopic) {
        return faqTopicRepository.save(faqTopic);
    }

    @Override
    public FAQTopic updateFAQTopic(int id, FAQTopicUpdateDto faqTopicUpdateDto) {
        FAQTopic faqTopic = faqTopicRepository.findById(id).get();
        faqTopic.setFaqCategoryId(faqTopic.getFaqCategoryId());
        faqTopic.setFaqTopicName(faqTopic.getFaqTopicName());
        faqTopic.setFaqCategory(faqCategoryService.getFAQCategory(faqTopicUpdateDto.getFaqCategoryId()));
        faqTopicRepository.save(faqTopic);
        return faqTopic;
    }

    @Override
    public FAQTopic deleteFAQTopic(int id) {
        FAQTopic faqTopic = faqTopicRepository.findById(id).get();
        faqTopicRepository.deleteById(id);
        return faqTopic;
    }
}