package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.Department;

import java.util.List;

public interface DepartmentService {

    public List<Department> getAllDepartment();

    public Department getDepartment(int id);

    public String addDepartment(Department department);

    public Department updateDepartment(int id, Department department);

    public String deleteDepartment(int id);
}
