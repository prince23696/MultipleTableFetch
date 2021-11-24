package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.EmployeeDetailsDto;
import com.MultipleTableFetch.Entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployee();

    public Employee getEmployee(int id);

    public String addEmployee(Employee employee);

    public Employee updateEmployee(int id, Employee employee);

    public String deleteEmployee(int id);

    public List<EmployeeDetailsDto> findByInnerJoin();
}
