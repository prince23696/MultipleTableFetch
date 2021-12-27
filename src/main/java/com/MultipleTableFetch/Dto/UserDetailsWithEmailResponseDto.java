package com.MultipleTableFetch.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsWithEmailResponseDto {

    private int recordCount;
    private List<UserDetailsWithEmailCheckDto> userDetailsWithEmailResponseDtoList;
}