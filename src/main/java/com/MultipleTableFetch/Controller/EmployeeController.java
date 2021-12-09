package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Dto.EmployeeDetailsResponseDto;
import com.MultipleTableFetch.Dto.EmployeeResponse;
import com.MultipleTableFetch.Dto.EmployeeResponsePagingDto;
import com.MultipleTableFetch.Entity.Employee;
import com.MultipleTableFetch.Service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("getEmployee/{id}")
    @ApiOperation(value = "Get Employee By ID")
    public Employee getEmployee(@PathVariable int id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("/getEmployees")
    public EmployeeResponse getAllEmployee() {
        List<Employee> employees = employeeService.getAllEmployee();
        return new EmployeeResponse(employees.size(), employees);
    }
/*

    @GetMapping("findBy")
    public APIResponse<Integer> findBy() {
        int record = employeeService.countRecords();
        return new APIResponse<Integer>(employeeService.findByInnerJoin().size(), record);

        //     return employeeService.findBy();
    }
*/

    @GetMapping("findByEmployeeWithPaging/{offset}/{pageSize}")
    public EmployeeResponsePagingDto findByEmployeeWithPaging(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Employee> employeeWithPaging = employeeService.findByEmployeeWithPaging(offset, pageSize);
        return new EmployeeResponsePagingDto(employeeWithPaging.getSize(), employeeWithPaging);
    }

    @GetMapping("findByEmployeeWithPagingAndSorting/{offset}/{pageSize}/{field}")
    public EmployeeResponsePagingDto findByEmployeeWithPagingANdSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        Page<Employee> employeeWithPagingAndSorting = employeeService.findByEmployeeWithPagingAndSorting(offset, pageSize, field);
        return new EmployeeResponsePagingDto(employeeWithPagingAndSorting.getSize(), employeeWithPagingAndSorting);
    }

    @GetMapping("/findByRecords")
    public EmployeeDetailsResponseDto findByRecords() {
        return employeeService.findByInnerJoin();

        // return employeeService.findByInnerJoin();
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