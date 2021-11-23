package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.Department;
import com.MultipleTableFetch.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartment(int id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    public String addDepartment(Department department) {
        departmentRepository.save(department);
        return "Department Added Successfully in database";
    }

    @Override
    public Department updateDepartment(int id, Department department) {
        Department department1 = departmentRepository.findById(id).get();
        department1.setDeptName(department.getDeptName());
        department1.setEmployee(department.getEmployee());
        departmentRepository.save(department1);
        return department;
    }

    @Override
    public String deleteDepartment(int id) {
        departmentRepository.deleteById(id);
        return "Department Deleted Successfully";
    }
}
