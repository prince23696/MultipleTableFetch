package com.MultipleTableFetch.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String salary;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
    private Gender gender;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
    private Department department;

    public Employee() {
    }

    public Employee(int id, String name, String salary, Gender gender, Department department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.gender = gender;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                ", gender=" + gender +
                ", department=" + department +
                '}';
    }
}
