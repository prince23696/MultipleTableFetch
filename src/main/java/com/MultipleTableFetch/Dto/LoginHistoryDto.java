package com.MultipleTableFetch.Dto;

import com.MultipleTableFetch.Entity.Users;

import java.util.Date;

public interface LoginHistoryDto {

    public Integer getLoginHistoryId();

    public Date getLoginTime();

    Integer getId();

    String getFullName();

    String getEmail();

    String getDateOfBirth();

    String getCountryId();

    String getRole();
}