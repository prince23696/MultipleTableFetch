package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Dto.LoginHistoryDto;
import com.MultipleTableFetch.Entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Integer> {

    @Query(value = "select count(login_history_id) from login_history ", nativeQuery = true)
    public int countRecords();

    @Query(value = "select l.login_history_id as loginHistoryId,l.login_time as loginTime,u.id as id, u.full_name as fullName, u.email as email,u.date_of_birth as dateOfBirth" +
            ",u.country_id as CountryId,u.role as Role from login_history l inner join users u on u.id=l.users_id", nativeQuery = true)
    public List<LoginHistoryDto> findByLoginDetailsDto();
}