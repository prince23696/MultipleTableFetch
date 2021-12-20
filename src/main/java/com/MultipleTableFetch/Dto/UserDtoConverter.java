package com.MultipleTableFetch.Dto;

import com.MultipleTableFetch.Entity.Users;

public class UserDtoConverter {

    public UserDtoClass userConverter(Users user) {
        UserDtoClass userDtoClass = new UserDtoClass();
        userDtoClass.setId(user.getId());
        userDtoClass.setFullName(user.getFullName());
        userDtoClass.setEmail(user.getEmail());
        userDtoClass.setDateOfBirth(user.getDateOfBirth());
        userDtoClass.setCountryId(user.getCountryId());
        userDtoClass.setRole(user.getRole());
        return userDtoClass;
    }
}
