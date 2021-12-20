package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Dto.LoginHistoryAdminDto;
import com.MultipleTableFetch.Dto.LoginHistoryAdminResponseDto;
import com.MultipleTableFetch.Entity.LoginHistoryAdmin;
import com.MultipleTableFetch.Repository.LoginHistoryAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginHistoryAdminServiceImpl implements LoginHistoryAdminService {

    @Autowired
    LoginHistoryAdminRepository loginHistoryAdminRepository;

    @Override
    public LoginHistoryAdmin saveLoginDetails(LoginHistoryAdmin loginHistoryAdmin) {
        return loginHistoryAdminRepository.save(loginHistoryAdmin);
    }

    @Override
    public LoginHistoryAdminResponseDto getLoginHistoryDetails() {

        int i = loginHistoryAdminRepository.countRecords();
        List<LoginHistoryAdminDto> byLoginDetailsDto = loginHistoryAdminRepository.findByLoginDetailsDto();
        LoginHistoryAdminResponseDto loginHistoryAdminResponseDto = new LoginHistoryAdminResponseDto();
        loginHistoryAdminResponseDto.setRecordCount(i);
        loginHistoryAdminResponseDto.setLoginHistoryDtoList(byLoginDetailsDto);
        return loginHistoryAdminResponseDto;
    }
}
