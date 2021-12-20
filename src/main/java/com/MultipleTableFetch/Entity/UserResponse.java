package com.MultipleTableFetch.Entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    public String email;
    public String token;
}