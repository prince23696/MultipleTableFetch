package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.LoginHistory;

import java.util.List;


public interface LoginHistoryService {

    public LoginHistory saveLoginDetails(LoginHistory loginHistory);

    public List<LoginHistory> getLoginHistoryDetails();
}
