package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.*;
import com.MultipleTableFetch.Entity.Admin;
import com.MultipleTableFetch.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.MultipleTableFetch.Controller.AdminController.token2;


@Service
public class AdminServiceImpl implements AdminService {

    public static ArrayList arrayList1 = new ArrayList<>();
    @Autowired
    AdminRepository adminRepository;

    @Override
    public AdminDtoClass addAdmin(Admin user) {
        try {
            if (user.getPassword().equals(user.getConfirmPassword())) {
                Admin save = adminRepository.save(user);
                AdminDtoConverter adminDtoConverter = new AdminDtoConverter();
                AdminDtoClass adminDtoClass = adminDtoConverter.adminConverter(save);
                return adminDtoClass;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AdminDetailsResponseDto getAllAdmin() {
        int by = adminRepository.countRecords();
        List<AdminDetailsDto> byAdminDetailsDto = adminRepository.findByAdminDetailsDto();
        AdminDetailsResponseDto adminDetailsResponseDto = new AdminDetailsResponseDto();
        adminDetailsResponseDto.setRecordCount(by);
        adminDetailsResponseDto.setAdminDetailsDtoList(byAdminDetailsDto);
        return adminDetailsResponseDto;
    }

    @Override
    public AdminDetailsResponseDto getAdmin(int id) {
        if (!arrayList1.contains(token2)) {
            int by = adminRepository.countRecords();
            List<AdminDetailsDto> byAdminDetailsDtoById = adminRepository.findByAdminDetailsDtoById(id);
            AdminDetailsResponseDto adminDetailsResponseDto = new AdminDetailsResponseDto();
            adminDetailsResponseDto.setRecordCount(by);
            adminDetailsResponseDto.setAdminDetailsDtoList(byAdminDetailsDtoById);
            return adminDetailsResponseDto;
        }
        return null;
    }

    @Override
    public AdminDtoClass updateAdmin(int id, Admin user) {
        if (!arrayList1.contains(token2)) {
            Admin user1 = adminRepository.findById(id).get();
            user1.setFullName(user.getFullName());
            user1.setEmail(user.getEmail());
            user1.setDateOfBirth(user.getDateOfBirth());
            user1.setRole(user.getRole());
            user1.setCountryId(user.getCountryId());
            user1.setPassword(user.getPassword());
            Admin save = adminRepository.save(user1);
            AdminDtoConverter adminDtoConverter = new AdminDtoConverter();
            AdminDtoClass adminDtoClass = adminDtoConverter.adminConverter(save);
            return adminDtoClass;
        }
        return null;
    }

    @Override
    public String deleteAdmin(int id) {
        if (!arrayList1.contains(token2)) {
            adminRepository.deleteById(id);
            return "Admin Deleted Successfully";
        }
        return "Admin logged out";
    }

    @Override
    public Admin getAdmin(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public LogoutResponseDto logoutAdmin(String email) {
        System.out.println(token2);
        arrayList1.add(token2);
        System.out.println(arrayList1);
        LogoutResponseDto logoutResponseDto = new LogoutResponseDto();
        logoutResponseDto.setMessage("Successfully Logout Admin");
        return logoutResponseDto;
    }

    @Override
    public AdminDtoClass changePassword(int id, String oldPassword, Admin admin) {
        Admin admin1 = adminRepository.findById(id).get();
        if (admin1.getPassword().equals(oldPassword)) {
            admin1.setPassword(admin.getPassword());
            admin1.setConfirmPassword(admin.getPassword());
            Admin save = adminRepository.save(admin1);
            AdminDtoConverter adminDtoConverter = new AdminDtoConverter();
            AdminDtoClass adminDtoClass = adminDtoConverter.adminConverter(save);
            return adminDtoClass;
        }
        return null;
    }

    @Override
    public AdminDtoClass findAdminByResetToken(String resetToken, String password) {
        Admin byEmail = adminRepository.findByResetToken(resetToken);
        //  Admin byEmail = adminRepository.findByEmail(email);
        System.out.println(byEmail);
        if (byEmail.getResetToken().equalsIgnoreCase(resetToken)) {
            byEmail.setPassword(password);
            byEmail.setConfirmPassword(password);
            Admin save = adminRepository.save(byEmail);
            AdminDtoConverter adminDtoConverter = new AdminDtoConverter();
            AdminDtoClass adminDtoClass = adminDtoConverter.adminConverter(save);
            return adminDtoClass;
        }
        return null;
    }

    @Override
    public AdminDtoClass getAdminDetailsByEmail(String email) {
        Admin byEmail = adminRepository.findByEmail(email);
        AdminDtoConverter adminDtoConverter = new AdminDtoConverter();
        AdminDtoClass adminDtoClass = adminDtoConverter.adminConverter(byEmail);
        return adminDtoClass;
    }

    @Override
    public AdminDtoClass updateResetTokenInUSer(String token, int id) {

        Admin user1 = adminRepository.findById(id).get();
        user1.setResetToken(token);
        Admin save = adminRepository.save(user1);
        AdminDtoConverter adminDtoConverter = new AdminDtoConverter();
        AdminDtoClass adminDtoClass = adminDtoConverter.adminConverter(save);
        return adminDtoClass;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin user = adminRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public boolean isValidAdmin(int id) {
        String username;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) auth.getPrincipal()).getUsername();
            if (username != null) {
                Admin user = adminRepository.findByEmail(username);
                if (user != null && user.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

}
