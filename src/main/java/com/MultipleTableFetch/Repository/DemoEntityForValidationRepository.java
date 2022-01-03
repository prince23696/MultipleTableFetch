package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.DemoEntityForValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoEntityForValidationRepository extends JpaRepository<DemoEntityForValidation, Long> {
}
