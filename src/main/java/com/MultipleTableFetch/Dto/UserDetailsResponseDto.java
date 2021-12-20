package com.MultipleTableFetch.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponseDto {

    private int recordCount;
    private List<UserDetailsDto> userDetailsDto;
}
