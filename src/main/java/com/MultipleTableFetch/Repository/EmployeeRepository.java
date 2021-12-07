package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Dto.EmployeeDetailsDto;
import com.MultipleTableFetch.Entity.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /*  @Query(value = "select * from employee e inner join department d " +
              "inner join gender g on e.id=g.employee_id", nativeQuery = true)*/
  /*  @Query(value = "select e.id,e.name,e.salary,(select g.gender from gender g) from employee e +" +
            " where e.id=g.employee_id ", nativeQuery = true)
  */

    @Query(value = "select e.id as id,e.name as name, e.salary as salary,g.gender as gender from employee e " +
            "inner join gender g on e.id=g.employee_id", nativeQuery = true)
    public List<EmployeeDetailsDto> findByInnerJoin();

    @Value("#{target.id+' target.name+'target.salary+'('+ target.gender.gender+' gender)'}")
    public List<Employee> findBy();

    @Query(value = "delete * from employee  where name='unlike' ", nativeQuery = true)
    public String deleteByName();


}