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
public class LoginHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loginHistoryId;
    private Date loginTime;
    @JsonBackReference
    @ManyToOne
    private Users users;
    public LoginHistory(Date loginTime, Users users) {
        this.loginTime = loginTime;
        this.users = users;
    }
}