package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Entity.Department;
import com.MultipleTableFetch.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("getDepartment/{id}")
    public Department getDepartment(@PathVariable int id) {
        Department department = departmentService.getDepartment(id);
        return department;
    }

    @GetMapping("/getDepartments")
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @PostMapping("/saveDepartment")
    public Department addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
        return department;
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return "Department deleted form database id-" + id;
    }

    @PutMapping("/updateDepartment/{id}")
    public Department updateDepartment(@PathVariable int id, @RequestBody Department department) {
        departmentService.updateDepartment(id, department);
        return department;
    }

}
