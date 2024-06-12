package com.green.greenfirstproject.user.model;

import com.green.greenfirstproject.user.dto.UserInsertDto;
import com.green.greenfirstproject.user.dto.UserUpdateDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long seq;
    private String id;
    private String pwd;
    private String name;
    private String email;
    private Integer gb;
    private Integer loginType;
    private String role ;
    private LocalDateTime inputDt;
    private LocalDateTime updateDt;

    public User(UserInsertDto data) {
        this.id = data.getId();
        this.pwd = data.getPw() ;
        this.name = data.getName();
        this.email = data.getEmail();
        this.gb = 1 ;
        this.loginType = data.getLoginType() ;
        this.role = "ROLE_USER" ;
        this.inputDt = LocalDateTime.now();
        this.updateDt = null ;
    }

    public void userDataChange(UserUpdateDto data) {
        if (data.getPw() != null) {
            this.pwd = data.getPw();
        }
        if (data.getName() != null) {
            this.name = data.getName();
        }
    }



}