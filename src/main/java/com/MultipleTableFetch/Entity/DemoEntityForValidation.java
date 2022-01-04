package com.MultipleTableFetch.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;


import javax.persistence.*;

import javax.validation.constraints.*;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemoEntityForValidation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Empty name Can not inserted")
    @NotNull
    @NotEmpty
    @Size(min = 4, message = "User Name Should have at least 4 characters")
    private String name;
    @NotBlank
    @Email
    private String email;
    // @Size(min = 3, max = 8)
    /*@Range(min = 3, max = 8, message = "provide min 3 digits and max 8 digits")*/
    // @Range(min = 3L, max = 5L, message = "provide min 3 digits and max 5 digits")
    @Digits(integer = 5, fraction = 0, message = "Range Exceed")
    @Min(value = 5)
    private String phone;
    @NotBlank
    @Size(min = 8, max = 16, message = "Password should At-least 8 Characters")
    private String password;
}