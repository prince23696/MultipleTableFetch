package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.FAQQuestionAnswerUpdateDto;
import com.MultipleTableFetch.Entity.FAQQuestionAnswer;
import com.MultipleTableFetch.Repository.FAQQuestionAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQQuestionAnswerServiceImpl implements FAQQuestionAnswerService {

    @Autowired
    FAQQuestionAnswerRepository faqQuestionAnswerRepository;
    @Autowired
    FAQTopicService faqTopicService;

    @Override
    public List<FAQQuestionAnswer> getAllFAQQuestionAnswer(int faqTopicId) {
        return faqQuestionAnswerRepository.findByFAQQuestionAnswerDetailsDto(faqTopicId);
    }

    @Override
    public FAQQuestionAnswer getFAQQuestionAnswer(int id) {
        return faqQuestionAnswerRepository.findById(id).get();
    }

    @Override
    public FAQQuestionAnswer addFAQQuestionAnswer(FAQQuestionAnswer faqQuestionAnswer) {
        return faqQuestionAnswerRepository.save(faqQuestionAnswer);
    }

    @Override
    public FAQQuestionAnswer updateFAQQuestionAnswer(int id, FAQQuestionAnswerUpdateDto faqQuestionAnswerUpdateDto) {
        FAQQuestionAnswer faqQuestionAnswer = faqQuestionAnswerRepository.findById(id).get();
        faqQuestionAnswer.setFaqTopicId(faqQuestionAnswerUpdateDto.getFaqTopicId());
        faqQuestionAnswer.setFaqQuestion(faqQuestionAnswerUpdateDto.getFaqQuestion());
        faqQuestionAnswer.setFaqAnswer(faqQuestionAnswerUpdateDto.getFaqAnswer());
        faqQuestionAnswer.setFaqTopic(faqTopicService.getFAQTopic(faqQuestionAnswerUpdateDto.getFaqTopicId()));
        faqQuestionAnswerRepository.save(faqQuestionAnswer);
        return faqQuestionAnswer;
    }

    @Override
    public FAQQuestionAnswer deleteFAQQuestionAnswer(int id) {
        FAQQuestionAnswer faqQuestionAnswer = faqQuestionAnswerRepository.findById(id).get();
        faqQuestionAnswerRepository.deleteById(id);
        return faqQuestionAnswer;
    }
}