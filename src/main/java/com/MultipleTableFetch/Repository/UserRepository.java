package com.MultipleTableFetch.Repository;

import com.MultipleTableFetch.Dto.UserDetailsDto;
import com.MultipleTableFetch.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    public Users findByEmail(String username);

    @Query(value = "select * from users u where u.reset_token=?1 ", nativeQuery = true)
    Users findByResetToken(String resetToken);

    @Query(value = "select count(id) from users ", nativeQuery = true)
    public int countRecords();

    @Query(value = "select u.id as id, u.full_name as fullName, u.email as email,u.date_of_birth as dateOfBirth,"
            + " u.country_id as CountryId,u.role as Role from users u", nativeQuery = true)
    public List<UserDetailsDto> findByUserDetailsDto();

    @Query(value = "select u.id as id, u.full_name as fullName, u.email as email,u.date_of_birth as dateOfBirth,"
            + " u.country_id as CountryId,u.role as Role from users u where u.id=?1", nativeQuery = true)
    public List<UserDetailsDto> findByUserDetailsDtoById(int id);
}