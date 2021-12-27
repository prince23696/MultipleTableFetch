package com.MultipleTableFetch.Dto;

public interface UserDetailsWithEmailCheckDto {

    int getId();

    String getFullName();

    String getEmail();

    String getDateOfBirth();

    String getCountryId();

    String getRole();

    Boolean getIsEmailExist();
}