package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public List<Users> getAllUser();

    public Users getUser(int id);

    public String addUser(Users user);

    public Users updateUser(int id, Users user);

    public String deleteUser(int id);

    public boolean isValidUser(int id);

}
