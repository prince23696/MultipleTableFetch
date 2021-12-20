package com.MultipleTableFetch.Dto;

import java.util.Date;

public interface LoginHistoryAdminDto {

    public Integer getLoginHistoryId();

    public Date getLoginTime();

    Integer getId();

    String getFullName();

    String getEmail();

    String getDateOfBirth();

    String getCountryId();

    String getRole();
}