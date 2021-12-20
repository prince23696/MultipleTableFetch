package com.MultipleTableFetch.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminDetailsResponseDto {

    private int recordCount;
    private List<AdminDetailsDto> adminDetailsDtoList;
}
