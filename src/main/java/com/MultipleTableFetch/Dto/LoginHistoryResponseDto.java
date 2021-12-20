package com.MultipleTableFetch.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginHistoryResponseDto {

    public int recordCount;
    public List<LoginHistoryDto> loginHistoryDtoList;
}