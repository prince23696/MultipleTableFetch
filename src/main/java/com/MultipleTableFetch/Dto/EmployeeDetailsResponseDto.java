package com.MultipleTableFetch.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailsResponseDto {

    private int recordCount;
    private List<EmployeeDetailsDto> employeeDetailsDtoList;

}