package com.MultipleTableFetch.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FAQCategoryDetailsResponseDto {

    private int recordCount;
    private List<FAQCategoryDetailsDto> faqCategoryDetailsDtoList;
}