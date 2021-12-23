package com.MultipleTableFetch.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDetailsResponseDto {

    private int recordCount;
    private List<CategoryDetailsDto> categoryDetailsDtoList;
}
