package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.LoginHistoryAdminResponseDto;
import com.MultipleTableFetch.Entity.LoginHistoryAdmin;

public interface LoginHistoryAdminService {

    public LoginHistoryAdmin saveLoginDetails(LoginHistoryAdmin loginHistoryAdmin);

    public LoginHistoryAdminResponseDto getLoginHistoryDetails();
}
