package com.MultipleTableFetch.Dto;

import com.MultipleTableFetch.Entity.Admin;

public class AdminDtoConverter {

    public AdminDtoClass adminConverter(Admin admin) {
        AdminDtoClass adminDtoClass = new AdminDtoClass();
        adminDtoClass.setId(admin.getId());
        adminDtoClass.setFullName(admin.getFullName());
        adminDtoClass.setEmail(admin.getEmail());
        adminDtoClass.setDateOfBirth(admin.getDateOfBirth());
        adminDtoClass.setCountryId(admin.getCountryId());
        adminDtoClass.setRole(admin.getRole());
        return adminDtoClass;
    }
}
