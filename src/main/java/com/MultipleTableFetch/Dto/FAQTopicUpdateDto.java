package com.MultipleTableFetch.Dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FAQTopicUpdateDto {

    private int faqCategoryId;
    private String faqTopicName;
}