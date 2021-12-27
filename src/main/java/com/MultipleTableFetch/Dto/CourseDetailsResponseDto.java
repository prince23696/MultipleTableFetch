package com.MultipleTableFetch.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailsResponseDto {

    private int recordCount;
    private List<CourseDetailsDto> courseDetailsDtoList;
}
