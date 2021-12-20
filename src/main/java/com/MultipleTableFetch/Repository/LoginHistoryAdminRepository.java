package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Dto.LoginHistoryAdminDto;
import com.MultipleTableFetch.Entity.LoginHistoryAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginHistoryAdminRepository extends JpaRepository<LoginHistoryAdmin, Integer> {

    @Query(value = "select count(login_history_id) from login_history_admin ", nativeQuery = true)
    public int countRecords();

    @Query(value = "select l.login_history_id as loginHistoryId,l.login_time as loginTime,u.id as id, u.full_name as fullName, u.email as email,u.date_of_birth as dateOfBirth" +
            ",u.country_id as CountryId,u.role as Role from login_history_admin l inner join admin u on u.id=l.admin_id", nativeQuery = true)
    public List<LoginHistoryAdminDto> findByLoginDetailsDto();
}