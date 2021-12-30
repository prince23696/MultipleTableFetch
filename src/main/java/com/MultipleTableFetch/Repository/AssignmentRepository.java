package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.CreateAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<CreateAssignment, Long> {

    @Query(value = "select count(id) from create_assignment ", nativeQuery = true)
    public int countRecords();
}