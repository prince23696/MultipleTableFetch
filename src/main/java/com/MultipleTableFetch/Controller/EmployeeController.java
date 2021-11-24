package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Dto.APIResponse;
import com.MultipleTableFetch.Dto.EmployeeDetailsDto;
import com.MultipleTableFetch.Entity.Employee;
import com.MultipleTableFetch.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("getEmployee/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("/getEmployees")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("findBy")
    public List<Employee> findBy() {
        return employeeService.findBy();
    }

    @GetMapping("findByEmployeeWithPaging/{offset}/{pageSize}")
    public APIResponse<Page<Employee>> findByEmployeeWithPaging(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Employee> employeeWithPaging = employeeService.findByEmployeeWithPaging(offset, pageSize);
        return new APIResponse<>(employeeWithPaging.getSize(), employeeWithPaging);
    }

    @GetMapping("findByEmployeeWithPagingAndSorting/{offset}/{pageSize}/{field}")
    public APIResponse<Page<Employee>> findByEmployeeWithPagingANdSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        Page<Employee> employeeWithPaging = employeeService.findByEmployeeWithPagingAndSorting(offset, pageSize, field);
        return new APIResponse<>(employeeWithPaging.getSize(), employeeWithPaging);
    }

    @GetMapping("/findByRecords")
    public List<EmployeeDetailsDto> findByRecords() {
        return employeeService.findByInnerJoin();
    }

    @PostMapping("/saveEmployee")
    public Employee addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return employee;
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted form database id-" + id;
    }

    @PutMapping("/updateEmployee/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
        return employee;
    }
}