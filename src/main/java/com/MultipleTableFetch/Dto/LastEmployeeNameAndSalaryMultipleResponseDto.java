package com.MultipleTableFetch.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LastEmployeeNameAndSalaryMultipleResponseDto {

    private int recordCount;
    private List<LastEmployeeNameAndSalaryMultipleDto> lastEmployeeNameAndSalaryMultipleDtolist;
}