package com.MultipleTableFetch.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private String email;
    private String dateOfBirth;
    // @Enumerated(EnumType.STRING)
    private String role;
    private Long countryId;
    private String password;
    private String confirmPassword;
    private String resetToken;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
    @JsonManagedReference
    List<LoginHistoryAdmin> loginHistoryAdminList;
}