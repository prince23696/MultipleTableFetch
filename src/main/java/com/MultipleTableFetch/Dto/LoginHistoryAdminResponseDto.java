package com.MultipleTableFetch.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginHistoryAdminResponseDto {

    public int recordCount;
    public List<LoginHistoryAdminDto> loginHistoryDtoList;
}