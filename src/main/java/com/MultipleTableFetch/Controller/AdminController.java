package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Dto.*;
import com.MultipleTableFetch.Entity.*;
import com.MultipleTableFetch.Helper.JwtUtil;
import com.MultipleTableFetch.Repository.AdminRepository;
import com.MultipleTableFetch.Service.*;
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
public class AdminController {

    public static String token2;
    @Autowired
    CourseService courseService;
    @Autowired
    AdminService adminService;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    LoginHistoryAdminService loginHistoryAdminService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    EmailService emailService;
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("getAdmin")
    public ResponseEntity<Object> getAdmins(@RequestParam int id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        AdminDetailsResponseDto admin = adminService.getAdmin(id);
        if (admin != null)
            return ResponseHandler.response(admin, "Successfully Getting Admin.", true, HttpStatus.OK);
        else
            return ResponseHandler.response("", "Error in Getting Admin Try Again...", false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getAdminDetailsByEmail")
    public ResponseEntity<Object> getAdminDetailsByEmail(@RequestParam String email, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        AdminDtoClass admin = adminService.getAdminDetailsByEmail(email);
        if (admin != null)
            return ResponseHandler.response(admin, "Successfully Getting Admin Details By Email.", true, HttpStatus.OK);
        else
            return ResponseHandler.response("", "Error in Getting Admin Try Again...", false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAdminLoginHistoryDetails")
    public ResponseEntity<Object> getAdminLoginHistoryDetails(@RequestParam int offset, @RequestParam int pageSize, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        LoginHistoryAdminResponseDto loginHistoryDetails = loginHistoryAdminService.getLoginHistoryDetails();
        return ResponseHandler.response(loginHistoryDetails, "Successfully Getting loginHistory Details", true, HttpStatus.OK);
    }

    @GetMapping("/getAllAdmin")
    public ResponseEntity<Object> getAllAdmin(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        AdminDetailsResponseDto allUser = adminService.getAllAdmin();
        return ResponseHandler.response(allUser, "Successfully Getting All Admins.", true, HttpStatus.OK);
    }

    @PostMapping("/signUpAdmin")
    public ResponseEntity<Object> addAdmins(@RequestBody Admin admin, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        if (admin.getRole().equals("ROLE_ADMIN")) {
            admin.getEmail().trim();
            AdminDtoClass adminDtoClass = adminService.addAdmin(admin);
            if (adminDtoClass != null)
                return ResponseHandler.response(adminDtoClass, "Successfully Created Admin With Given Details.", true, HttpStatus.OK);
            else
                return ResponseHandler.response("", "Admin Creations Failure Check Details and Try Again.", false, HttpStatus.BAD_REQUEST);
        }
        return ResponseHandler.response("", "Operation Failure Try Again", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/logoutAdmin}")
    public ResponseEntity<Object> logoutAdmin(@RequestParam String email) {
        LogoutResponseDto logoutResponseDto = adminService.logoutAdmin(email);
        return ResponseHandler.response(logoutResponseDto, "", true, HttpStatus.OK);

    }

    @PostMapping("/changeAdminPassword")
    public ResponseEntity<Object> changeAdminPassword(@RequestParam int id, @RequestParam String oldPassword, @RequestBody Admin admin, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        AdminDtoClass admin1 = adminService.changePassword(id, oldPassword, admin);
        if (admin1 != null)
            return ResponseHandler.response(admin1, "Admin Password Changed Successfully.", true, HttpStatus.OK);
        else
            return ResponseHandler.response("", "Error Password Not Changed.", false, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteAdmin")
    public String deleteAdmin(@RequestParam int id) {
        adminService.deleteAdmin(id);
        return "Users deleted form database id-" + id;
    }

    @PutMapping("/updateAdmin")
    public ResponseEntity<Object> updateAdmin(@RequestParam int id, @RequestBody Admin admin, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        AdminDtoClass updatedAdmin = adminService.updateAdmin(id, admin);
        if (updatedAdmin != null)
            return ResponseHandler.response(updatedAdmin, "Admin Updated Successfully.", true, HttpStatus.OK);
        else
            return ResponseHandler.response("", "Admin Not Updated Check Details.", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/forgotPasswordAdmin")
    public ResponseEntity<Object> processForgotPasswordAdmin(@RequestBody ForgetPasswordDto forgetPasswordDto, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        String email = forgetPasswordDto.getEmail();
        String token = RandomString.make(30);
        try {
            AdminDtoClass adminDetailsByEmail = adminService.getAdminDetailsByEmail(forgetPasswordDto.getEmail());
            adminService.updateResetTokenInUSer(token, adminDetailsByEmail.getId());
            String resetPasswordLink = "/reset_password?token=" + token;
            return new ResponseEntity<>(new EmailResponse(email, resetPasswordLink), HttpStatus.OK);
        } catch (UsernameNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @PostMapping("/findAdminByResetToken")
    public ResponseEntity<Object> findAdminByResetToken(@RequestParam String resetToken, @RequestParam String newPassword, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        AdminDtoClass adminByResetToken = adminService.findAdminByResetToken(resetToken, newPassword);
        if (adminByResetToken != null)
            return ResponseHandler.response(adminByResetToken, "Successfully Performed Forget Password Operation.", true, HttpStatus.OK);
        else
            return ResponseHandler.response("", "Token Not Matched Operation Failure.", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signInAdmin")
    public ResponseEntity<Object> generateAdminToken(@RequestBody AdminDto admin, @RequestHeader(name = "Accept-Language", required = false) Locale locale) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(admin.getEmail(), admin.getPassword()));
            Admin admin1 = adminRepository.findByEmail(admin.getEmail());
            UserDetails userDetails = this.adminService.loadUserByUsername(admin.getEmail());
            if (admin1.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
                String token = this.jwtUtil.generateToken(userDetails);
                System.out.println("JWT : " + token);
                token2 = token;
                LoginHistoryAdmin loginHistoryAdmin = new LoginHistoryAdmin(Calendar.getInstance().getTime(), admin1);
                LoginHistoryAdmin loginHistoryAdmin1 = loginHistoryAdminService.saveLoginDetails(loginHistoryAdmin);
                return ResponseEntity.ok(new AdminResponse(admin.getEmail(), token));
            }
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }
        return new ResponseEntity<>("ROLE NOT ACCEPTED", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/sendEmailAdmin")
    public ResponseEntity<Object> SendEmail(@RequestBody Email email, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        String s = emailService.sendMailMessage(email.getSubject(), email.getMessage(), email.getTo());
        return ResponseHandler.response(s, "Email Send Successfully.", true, HttpStatus.OK);
    }

    @PostMapping("/checkAdminEmailExistOrNot")
    public ResponseEntity<Object> checkAdminEmailExistOrNot(@RequestParam String email, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {

        Boolean aBoolean = adminService.checkAdminEmailExistOrNot(email);
        if (aBoolean)
            return ResponseHandler.response("", "Admin Email Already Exists.", true, HttpStatus.OK);
        else
            return ResponseHandler.response("", "Admin Email Available For Creating Account.", false, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/ApproveRejectCourse")
    public ResponseEntity<Object> ApproveRejectCourse(@RequestParam Long courseId, @RequestParam CourseEnum status, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        Course course = courseService.getCourse(courseId);
        String s = String.valueOf(status);
        course.setStatus(s);
        Course course1 = courseService.updateCourse(courseId, course);
        return ResponseHandler.response(course1, "Operation Performed On Api.", true, HttpStatus.OK);
        //  return ResponseHandler.response("", "Admin Not Updated Check Details.", false, HttpStatus.BAD_REQUEST);
    }
}