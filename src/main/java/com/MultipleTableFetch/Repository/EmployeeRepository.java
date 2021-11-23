package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "select * from employee e inner join department d on e.id=d.employee_id " +
            "inner join gender g on e.id=g.employee_id", nativeQuery = true)
    public List<Employee> findByInnerJoin();
}
