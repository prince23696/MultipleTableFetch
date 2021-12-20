package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Dto.AdminDetailsDto;
import com.MultipleTableFetch.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    public Admin findByEmail(String username);

    @Query(value = "select * from admin a where u.reset_token=?1 ", nativeQuery = true)
    Admin findByResetToken(String resetToken);

    @Query(value = "select count(id) from admin ", nativeQuery = true)
    public int countRecords();

    @Query(value = "select u.id as id, u.full_name as fullName, u.email as email,u.date_of_birth as dateOfBirth,"
            + " u.country_id as CountryId,u.role as Role from admin u", nativeQuery = true)
    public List<AdminDetailsDto> findByAdminDetailsDto();

    @Query(value = "select u.id as id, u.full_name as fullName, u.email as email,u.date_of_birth as dateOfBirth,"
            + " u.country_id as CountryId,u.role as Role from admin u where u.id=?1", nativeQuery = true)
    public List<AdminDetailsDto> findByAdminDetailsDtoById(int id);
}