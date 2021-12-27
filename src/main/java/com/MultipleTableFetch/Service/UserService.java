package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.LogoutResponseDto;
import com.MultipleTableFetch.Dto.UserDetailsResponseDto;

import com.MultipleTableFetch.Dto.UserDetailsWithEmailResponseDto;
import com.MultipleTableFetch.Dto.UserDtoClass;
import com.MultipleTableFetch.Entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public UserDetailsResponseDto getAllUser();

    public UserDetailsResponseDto getUser(int id);

    public Users getUserUsingId(int id);

    public UserDtoClass addUser(Users user);

    public UserDtoClass updateUser(int id, Users user);

    public String deleteUser(int id);

    public Users getUsers(String email);

    public LogoutResponseDto logoutUser(String email);

    public UserDtoClass changePassword(int id, String oldPassword, Users users);

    public UserDtoClass findUserByResetToken(String resetToken, String password);

    public UserDtoClass getUserDetailsByEmail(String email);

    public UserDtoClass updateResetTokenInUSer(String token, int id);

    public Boolean checkEmailExistOrNot(String email);

    public UserDetailsWithEmailResponseDto checkEmailAndFetchSpecificData(String email);

    public boolean isValidUser(int id);
}