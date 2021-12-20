package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Integer> {
}
