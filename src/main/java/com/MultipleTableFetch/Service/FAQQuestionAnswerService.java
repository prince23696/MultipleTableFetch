package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.FAQQuestionAnswerUpdateDto;
import com.MultipleTableFetch.Entity.FAQQuestionAnswer;

import java.util.List;

public interface FAQQuestionAnswerService {

    public List<FAQQuestionAnswer> getAllFAQQuestionAnswer(int faqTopicId);

    public FAQQuestionAnswer getFAQQuestionAnswer(int id);

    public FAQQuestionAnswer addFAQQuestionAnswer(FAQQuestionAnswer faqQuestionAnswer);

    public FAQQuestionAnswer updateFAQQuestionAnswer(int id, FAQQuestionAnswerUpdateDto faqQuestionAnswerUpdateDto);

    public FAQQuestionAnswer deleteFAQQuestionAnswer(int id);
}