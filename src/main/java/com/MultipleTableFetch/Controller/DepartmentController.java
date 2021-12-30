package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Entity.Department;
import com.MultipleTableFetch.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("getDepartment")
    public Department getDepartment(@RequestParam int id) {
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

    @DeleteMapping("/deleteDepartment")
    public String deleteDepartment(@RequestParam int id) {
        departmentService.deleteDepartment(id);
        return "Department deleted form database id-" + id;
    }

    @PutMapping("/updateDepartment")
    public Department updateDepartment(@RequestParam int id, @RequestBody Department department) {
        departmentService.updateDepartment(id, department);
        return department;
    }
}