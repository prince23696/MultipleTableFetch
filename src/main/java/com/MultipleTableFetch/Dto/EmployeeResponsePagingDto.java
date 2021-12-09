package com.MultipleTableFetch.Dto;

import com.MultipleTableFetch.Entity.Employee;
import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponsePagingDto {

    private int recordCount;
    private Page<Employee> employeeResponsePagingDto;

}
