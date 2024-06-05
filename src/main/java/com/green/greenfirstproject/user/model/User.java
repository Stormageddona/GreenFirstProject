package com.green.greenfirstproject.user.model;

import com.green.greenfirstproject.user.dto.UserInsertDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}