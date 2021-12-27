package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Dto.*;
import com.MultipleTableFetch.Entity.Email;
import com.MultipleTableFetch.Entity.LoginHistory;
import com.MultipleTableFetch.Entity.UserResponse;
import com.MultipleTableFetch.Entity.Users;
import com.MultipleTableFetch.Helper.JwtUtil;
import com.MultipleTableFetch.Service.EmailService;
import com.MultipleTableFetch.Service.LoginHistoryService;
import com.MultipleTableFetch.Service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Locale;

@RestController
public class UserController {

    public static String token1;

    @Autowired
    UserService userService;
    @Autowired
    LoginHistoryService loginHistoryService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    EmailService emailService;
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("getUser")
    public ResponseEntity<Object> getUsers(@RequestParam int id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        UserDetailsResponseDto user = userService.getUser(id);
        if (user != null)
            return ResponseHandler.response(user, "Successfully Getting Users.", true, HttpStatus.OK);
        else
            return ResponseHandler.response("", "Error in Getting User Try Again...", false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getUserDetailsByEmail")
    public ResponseEntity<Object> getUserDetailsByEmail(@RequestParam String email, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        UserDtoClass user = userService.getUserDetailsByEmail(email);
        if (user != null)
            return ResponseHandler.response(user, "Successfully Getting Users Details By Email.", true, HttpStatus.OK);
        else
            return ResponseHandler.response("", "Error in Getting User Try Again...", false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getLoginHistoryDetails")
    public ResponseEntity<Object> getLoginHistoryDetails(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        LoginHistoryResponseDto loginHistoryDetails = loginHistoryService.getLoginHistoryDetails();
        return ResponseHandler.response(loginHistoryDetails, "Successfully Getting loginHistory Details", true, HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<Object> getAllUsers(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        UserDetailsResponseDto allUser = userService.getAllUser();
        return ResponseHandler.response(allUser, "Successfully Getting All Users.", true, HttpStatus.OK);
    }

    @PostMapping("/signUpUser")
    public ResponseEntity<Object> addUsers(@RequestBody Users user, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        if (user.getRole().equals("ROLE_USER")) {
            UserDtoClass userDtoClass = userService.addUser(user);
            if (userDtoClass != null)
                return ResponseHandler.response(userDtoClass, "Successfully Created User With Given Details.", true, HttpStatus.OK);
            else
                return ResponseHandler.response("", "User Creations Failure Check Details and Try Again.", false, HttpStatus.BAD_REQUEST);
        }
        return ResponseHandler.response("", "Operation Failure Try Again", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/logoutUser")
    public ResponseEntity<Object> logoutUser(@RequestParam String email) {
        LogoutResponseDto logoutResponseDto = userService.logoutUser(email);
        return ResponseHandler.response(logoutResponseDto, "", true, HttpStatus.OK);

    }

    @PostMapping("/changePassword")
    public ResponseEntity<Object> logoutUser(@RequestParam int id, @RequestParam String oldPassword, @RequestBody Users users, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        UserDtoClass users1 = userService.changePassword(id, oldPassword, users);
        if (users1 != null)
            return ResponseHandler.response(users1, "Password Changed Successfully.", true, HttpStatus.OK);
        else
            return ResponseHandler.response("", "Error Password Not Changed.", false, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUsers(@RequestParam int id) {
        userService.deleteUser(id);
        return "Users deleted form database id-" + id;
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Object> updateUsers(@RequestParam int id, @RequestBody Users user, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        UserDtoClass users = userService.updateUser(id, user);
        if (users != null)
            return ResponseHandler.response(users, "User Updated Successfully.", true, HttpStatus.OK);
        else
            return ResponseHandler.response("", "User Not Updated Check Details.", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<Object> processForgotPassword(@RequestBody ForgetPasswordDto forgetPasswordDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        String email = forgetPasswordDto.getEmail();
        String token = RandomString.make(30);

        try {
            UserDtoClass userDetailsByEmail = userService.getUserDetailsByEmail(forgetPasswordDto.getEmail());
            userService.updateResetTokenInUSer(token, userDetailsByEmail.getId());
            String resetPasswordLink = "/reset_password?token=" + token;
            return new ResponseEntity<>(new EmailResponse(email, resetPasswordLink), HttpStatus.OK);
        } catch (UsernameNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @PostMapping("/findUserByResetToken")
    public ResponseEntity<Object> findUserByResetToken(@RequestParam String resetToken, @RequestParam String newPassword, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        UserDtoClass userByResetToken = userService.findUserByResetToken(resetToken, newPassword);
        if (userByResetToken != null)
            return ResponseHandler.response(userByResetToken, "Successfully Performed Forget Password Operation.", true, HttpStatus.OK);
        else
            return ResponseHandler.response("", "Forget Password Operation Failure.", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signInUser")
    public ResponseEntity<Object> generateToken(@RequestBody UserDto user, @RequestHeader(name = "Accept-Language", required = false) Locale locale) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            Users users = userService.getUsers(user.getEmail());
            UserDetails userDetails = this.userService.loadUserByUsername(user.getEmail());
            if (users.getRole().equalsIgnoreCase("ROLE_USER")) {
                String token = this.jwtUtil.generateToken(userDetails);
                System.out.println("JWT : " + token);
                token1 = token;
                LoginHistory loginHistory = new LoginHistory(Calendar.getInstance().getTime(), users);
                LoginHistory loginHistory1 = loginHistoryService.saveLoginDetails(loginHistory);
                return ResponseEntity.ok(new UserResponse(user.getEmail(), token));
            }
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }
        return new ResponseEntity<>("ROLE NOT ACCEPTED", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<Object> SendEmail(@RequestBody Email email, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        String s = emailService.sendMailMessage(email.getSubject(), email.getMessage(), email.getTo());
        return ResponseHandler.response(s, "Email Send Successfully.", true, HttpStatus.OK);
    }

    @PostMapping("/checkEmailExistOrNot")
    public ResponseEntity<Object> checkEmailExistOrNot(@RequestParam String email, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {

        Boolean aBoolean = userService.checkEmailExistOrNot(email);
        if (aBoolean)
            return ResponseHandler.response(aBoolean, "Email Already Exists. ", true, HttpStatus.OK);
        else
            return ResponseHandler.response("", "Email Available For Creating Account.", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/checkEmailAndFetchSpecificData")
    public ResponseEntity<Object> checkEmailAndFetchSpecificData(@RequestParam String email, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {

        UserDetailsWithEmailResponseDto userDetailsWithEmailResponseDto = userService.checkEmailAndFetchSpecificData(email);
        if (!userDetailsWithEmailResponseDto.getUserDetailsWithEmailResponseDtoList().isEmpty())
            return ResponseHandler.response(userDetailsWithEmailResponseDto, "Specific Data For User Using Email Id. ", true, HttpStatus.OK);
        else
            return ResponseHandler.response("", "Email Not Exist in Database.", false, HttpStatus.BAD_REQUEST);
    }
}