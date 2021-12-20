package com.MultipleTableFetch.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginHistoryAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loginHistoryId;
    private Date loginTime;
    @JsonBackReference
    @ManyToOne
    private Admin admin;

    public LoginHistoryAdmin(Date loginTime, Admin admin) {
        this.loginTime = loginTime;
        this.admin = admin;
    }
}