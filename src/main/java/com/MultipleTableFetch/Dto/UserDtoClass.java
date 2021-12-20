package com.MultipleTableFetch.Dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoClass {

    int id;
    String fullName;
    String email;
    String dateOfBirth;
    Long countryId;
    String role;
}
