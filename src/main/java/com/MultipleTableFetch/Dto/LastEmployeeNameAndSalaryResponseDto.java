package com.MultipleTableFetch.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LastEmployeeNameAndSalaryResponseDto {

    private int recordCount;
    private LastEmployeeNameAndSalaryDto lastEmployeeNameAndSalaryList;
}