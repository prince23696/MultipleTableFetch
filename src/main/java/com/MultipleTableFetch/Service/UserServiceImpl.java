package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.*;
import com.MultipleTableFetch.Entity.GuideRating;
import com.MultipleTableFetch.Entity.Users;
import com.MultipleTableFetch.Repository.GuideRatingRepository;
import com.MultipleTableFetch.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.MultipleTableFetch.Controller.UserController.token1;

@Service
public class UserServiceImpl implements UserService {

    public static ArrayList arrayList = new ArrayList<>();
    @Autowired
    UserRepository usersRepository;
    @Autowired
    GuideRatingRepository guideRatingRepository;

    @Override
    public UserDtoClass addUser(Users user) {
        try {
            if (user.getPassword().equals(user.getConfirmPassword())) {
                Users save = usersRepository.save(user);
                UserDtoConverter userDtoConverter = new UserDtoConverter();
                UserDtoClass userDtoClass = userDtoConverter.userConverter(save);
                return userDtoClass;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDetailsResponseDto getAllUser() {
        int by = usersRepository.countRecords();
        List<UserDetailsDto> byUserDetailsDto = usersRepository.findByUserDetailsDto();
        UserDetailsResponseDto userDetailsResponseDto = new UserDetailsResponseDto();
        userDetailsResponseDto.setRecordCount(by);
        userDetailsResponseDto.setUserDetailsDto(byUserDetailsDto);
        return userDetailsResponseDto;
    }

    @Override
    public UserDetailsResponseDto getUser(int id) {
        // if (isValidUser(id)) {
        if (!arrayList.contains(token1)) {
            int by = usersRepository.countRecords();
            List<UserDetailsDto> byUserDetailsDto = usersRepository.findByUserDetailsDtoById(id);
            UserDetailsResponseDto userDetailsResponseDto = new UserDetailsResponseDto();
            userDetailsResponseDto.setRecordCount(by);
            userDetailsResponseDto.setUserDetailsDto(byUserDetailsDto);
            return userDetailsResponseDto;
        }
        return null;
    }

    @Override
    public Users getUserUsingId(int id) {
        return usersRepository.findById(id).get();
    }

    @Override
    public UserDtoClass updateUser(int id, Users user) {
        //    if (isValidUser(id)) {
        if (!arrayList.contains(token1)) {
            Users user1 = usersRepository.findById(id).get();
            user1.setFullName(user.getFullName());
            user1.setEmail(user.getEmail());
            user1.setDateOfBirth(user.getDateOfBirth());
            user1.setRole(user.getRole());
            user1.setCountryId(user.getCountryId());
            user1.setPassword(user.getPassword());
            Users save = usersRepository.save(user1);
            UserDtoConverter userDtoConverter = new UserDtoConverter();
            UserDtoClass userDtoClass = userDtoConverter.userConverter(save);
            return userDtoClass;
        }
        //   }
        return null;
    }

    @Override
    public String deleteUser(int id) {
        if (!arrayList.contains(token1)) {
            usersRepository.deleteById(id);
            return "User Deleted Successfully";
        }
        return "User logged out";
    }

    @Override
    public Users getUsers(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public LogoutResponseDto logoutUser(String email) {
        arrayList.add(token1);
        LogoutResponseDto logoutResponseDto = new LogoutResponseDto();
        logoutResponseDto.setMessage("Successfully Logout User");
        return logoutResponseDto;
    }

    @Override
    public UserDtoClass changePassword(int id, String oldPassword, Users user) {
        Users user1 = usersRepository.findById(id).get();
        if (user1.getPassword().equals(oldPassword)) {
            user1.setPassword(user.getPassword());
            user1.setConfirmPassword(user.getPassword());
            Users save = usersRepository.save(user1);
            UserDtoConverter userDtoConverter = new UserDtoConverter();
            UserDtoClass userDtoClass = userDtoConverter.userConverter(save);
            return userDtoClass;
        }
        return null;
    }

    @Override
    public UserDtoClass findUserByResetToken(String resetToken, String password) {
        Users byEmail = usersRepository.findByResetToken(resetToken);
        if (byEmail.getResetToken().equalsIgnoreCase(resetToken)) {
            byEmail.setPassword(password);
            byEmail.setConfirmPassword(password);
            Users save = usersRepository.save(byEmail);
            UserDtoConverter userDtoConverter = new UserDtoConverter();
            UserDtoClass userDtoClass = userDtoConverter.userConverter(save);
            return userDtoClass;
        }
        return null;
    }

    @Override
    public UserDtoClass getUserDetailsByEmail(String email) {
        Users byEmail = usersRepository.findByEmail(email);
        UserDtoConverter userDtoConverter = new UserDtoConverter();
        UserDtoClass userDtoClass = userDtoConverter.userConverter(byEmail);
        return userDtoClass;
    }

    @Override
    public UserDtoClass updateResetTokenInUSer(String token, int id) {

        Users user1 = usersRepository.findById(id).get();
        user1.setResetToken(token);
        Users save = usersRepository.save(user1);
        UserDtoConverter userDtoConverter = new UserDtoConverter();
        UserDtoClass userDtoClass = userDtoConverter.userConverter(save);
        return userDtoClass;
    }

    @Override
    public Boolean checkEmailExistOrNot(String email) {
       /* Boolean aBoolean = usersRepository.checkEmailExistInUserOrNot(email);
        return aBoolean;*/
        if (usersRepository.checkEmailExistInUserOrNot(email)) {
            return true;
        } else if (usersRepository.checkEmailExistAdminOrNot(email)) {
            return true;
        } else
            return false;
    }

    @Override
    public UserDetailsWithEmailResponseDto checkEmailAndFetchSpecificData(String email) {
        int by = usersRepository.countRecords();
        List<UserDetailsWithEmailCheckDto> userDetailsWithEmailCheckDtos = usersRepository.checkEmailAndFetchSpecificData(email);
        UserDetailsWithEmailResponseDto userDetailsWithEmailResponseDto = new UserDetailsWithEmailResponseDto();
        userDetailsWithEmailResponseDto.setRecordCount(by);
        userDetailsWithEmailResponseDto.setUserDetailsWithEmailResponseDtoList(userDetailsWithEmailCheckDtos);
        return userDetailsWithEmailResponseDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = usersRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public boolean isValidUser(int id) {
        String username;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) auth.getPrincipal()).getUsername();
            if (username != null) {
                Users user = usersRepository.findByEmail(username);
                if (user != null && user.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public GuideRating addGuideRating(GuideRating guideRating) {
        return guideRatingRepository.save(guideRating);
    }

    @Override
    public List<GuideRating> getAllGuide() {
        return guideRatingRepository.findAll();
    }

    @Override
    public GuideRating getGuideRating(Long guideId) {
        return guideRatingRepository.findById(guideId).get();
    }
}