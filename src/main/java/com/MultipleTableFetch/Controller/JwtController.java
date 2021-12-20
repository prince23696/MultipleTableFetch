/*package com.MultipleTableFetch.Controller;*/
/*


import com.MultipleTableFetch.Entity.UserResponse;
import com.MultipleTableFetch.Entity.Users;
import com.MultipleTableFetch.Helper.JwtUtil;
import com.MultipleTableFetch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JwtController {

    public static String token1;

    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody Users user) throws Exception {
        System.out.println(user);
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }
        Users users = userService.getUsers(user.getEmail());
        UserDetails userDetails = this.userService.loadUserByUsername(user.getEmail());
        if (users.getRole().equalsIgnoreCase("ROLE_USER") || users.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
            String token = this.jwtUtil.generateToken(userDetails);
            System.out.println("JWT : " + token);
            token1 = token;
            System.out.println("token in jwt file"+token1);
            return ResponseEntity.ok(new UserResponse(user.getEmail(), token));
        }
        return new ResponseEntity<String>("ROLE NOT ACCEPTED", HttpStatus.BAD_REQUEST);
    }
}
*/
