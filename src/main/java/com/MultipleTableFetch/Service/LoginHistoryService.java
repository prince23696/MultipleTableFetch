package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.LoginHistoryResponseDto;
import com.MultipleTableFetch.Entity.LoginHistory;

public interface LoginHistoryService {

    public LoginHistory saveLoginDetails(LoginHistory loginHistory);

    public LoginHistoryResponseDto getLoginHistoryDetails();
}
