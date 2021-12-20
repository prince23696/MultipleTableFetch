package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.LoginHistoryDto;
import com.MultipleTableFetch.Dto.LoginHistoryResponseDto;
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
    public LoginHistoryResponseDto getLoginHistoryDetails() {

        int i = loginHistoryRepository.countRecords();
        List<LoginHistoryDto> byLoginDetailsDto = loginHistoryRepository.findByLoginDetailsDto();
        LoginHistoryResponseDto loginHistoryResponseDto = new LoginHistoryResponseDto();
        loginHistoryResponseDto.setRecordCount(i);
        loginHistoryResponseDto.setLoginHistoryDtoList(byLoginDetailsDto);
        return loginHistoryResponseDto;
    }
}