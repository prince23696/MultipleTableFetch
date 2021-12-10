package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.Users;
import com.MultipleTableFetch.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository usersRepository;

    @Override
    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUser(int id) {
        return usersRepository.findById(id).get();
    }

    @Override
    public String addUser(Users user) {
        usersRepository.save(user);
        return "User Registration Successfully";
    }

    @Override
    public Users updateUser(int id, Users user) {
        Users user1 = usersRepository.findById(id).get();
        user1.setFullName(user.getFullName());
        user1.setEmail(user.getEmail());
        user1.setDateOfBirth(user.getDateOfBirth());
        user1.setRole(user.getRole());
        user1.setCountryId(user.getCountryId());
        user1.setPassword(user.getPassword());
        usersRepository.save(user1);
        return user1;
    }

    @Override
    public String deleteUser(int id) {
        usersRepository.deleteById(id);
        return "User Deleted Successfully";
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
}
