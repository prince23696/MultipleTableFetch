package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.LoginHistory;
import com.MultipleTableFetch.Repository.LoginHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {

    @Autowired
    LoginHistoryRepository loginHistoryRepository;

    @Override
    public LoginHistory saveLoginDetails(LoginHistory loginHistory) {
        return loginHistoryRepository.save(loginHistory);
    }

    @Override
    public List<LoginHistory> getLoginHistoryDetails() {
        return loginHistoryRepository.findAll();
    }
}