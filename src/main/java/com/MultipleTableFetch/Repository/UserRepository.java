package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    public Users findByEmail(String username);
}