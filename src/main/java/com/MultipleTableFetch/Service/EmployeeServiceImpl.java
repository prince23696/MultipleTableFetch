package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.EmployeeDetailsDto;
import com.MultipleTableFetch.Entity.Employee;
import com.MultipleTableFetch.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public String addEmployee(Employee employee) {
        //     if (employee.getName().equalsIgnoreCase("like")) {
        employeeRepository.save(employee);
        return "Employee Saved in database";
   /*     } else if (employee.getName().equalsIgnoreCase("unlike")) {
            String s = employeeRepository.deleteByName();
            return s;
        } else {
            Employee employee1 = employeeRepository.findById(employee.getId()).get();
            employee1.setName(employee.getName());
            employee1.setDepartment(employee.getDepartment());
            employee1.setSalary(employee.getSalary());
            employee1.setGender(employee.getGender());
            employeeRepository.save(employee1);
            return "update employee";
        }*/
    }


    @Override
    public Employee updateEmployee(int id, Employee employee) {

        Employee employee1 = employeeRepository.findById(id).get();
        employee1.setName(employee.getName());
        employee1.setDepartment(employee.getDepartment());
        employee1.setSalary(employee.getSalary());
        employee1.setGender(employee.getGender());
        employeeRepository.save(employee1);
        return employee1;
    }

    @Override
    public String deleteEmployee(int id) {
        employeeRepository.deleteById(id);
        return "Employee Record Delete Successfully";
    }

    @Override
    public List<EmployeeDetailsDto> findByInnerJoin() {
        return employeeRepository.findByInnerJoin();
    }

    @Override
    public List<Employee> findBy() {
        return employeeRepository.findBy();
    }

    @Override
    public Page<Employee> findByEmployeeWithPaging(int offset, int pageSize) {
        return employeeRepository.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public Page<Employee> findByEmployeeWithPagingAndSorting(int offset, int pageSize, String field) {
        return employeeRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field).descending()));

    }
}