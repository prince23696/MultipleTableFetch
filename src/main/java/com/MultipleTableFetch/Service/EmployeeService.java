package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.EmployeeDetailsResponseDto;
import com.MultipleTableFetch.Dto.LastEmployeeNameAndSalaryMultipleResponseDto;
import com.MultipleTableFetch.Dto.LastEmployeeNameAndSalaryResponseDto;
import com.MultipleTableFetch.Entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployee();

    public Employee getEmployee(int id);

    public String addEmployee(Employee employee);

    public Employee updateEmployee(int id, Employee employee);

    public String deleteEmployee(int id);

    public EmployeeDetailsResponseDto findByInnerJoin();

    public int countRecords();

    public Page<Employee> findByEmployeeWithPaging(int offset, int pageSize);

    public Page<Employee> findByEmployeeWithPagingAndSorting(int offset, int pageSize, String field);

    public LastEmployeeNameAndSalaryResponseDto fetchByLastSalary();

    public LastEmployeeNameAndSalaryMultipleResponseDto fetchByLastSalaryMultiple();
}
