package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.LogoutResponseDto;
import com.MultipleTableFetch.Dto.AdminDetailsResponseDto;
import com.MultipleTableFetch.Dto.AdminDtoClass;
import com.MultipleTableFetch.Entity.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService extends UserDetailsService {

    public AdminDetailsResponseDto getAllAdmin();

    public AdminDetailsResponseDto getAdmin(int id);

    public AdminDtoClass addAdmin(Admin user);

    public AdminDtoClass updateAdmin(int id, Admin user);

    public String deleteAdmin(int id);

    public Admin getAdmin(String email);

    public LogoutResponseDto logoutAdmin(String email);

    public AdminDtoClass changePassword(int id, String oldPassword, Admin admin);

    public AdminDtoClass findAdminByResetToken(String resetToken, String password);

    public AdminDtoClass getAdminDetailsByEmail(String email);

    public AdminDtoClass updateResetTokenInUSer(String token, int id);

    public Boolean checkAdminEmailExistOrNot(String email);

    public boolean isValidAdmin(int id);
}