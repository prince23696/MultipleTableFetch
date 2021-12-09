package com.MultipleTableFetch.Dto;

import com.MultipleTableFetch.Entity.Employee;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    int recordCount;
    List<Employee> employeeList;

}
