package com.MultipleTableFetch.Dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FAQQuestionAnswerUpdateDto {

    private int faqTopicId;
    private String faqQuestion;
    private String faqAnswer;
}
