package com.green.greenfirstproject.user.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    @JsonIgnore
    private long user_seq;

    private String userId;
    private String userPw;
    private String user_nm;
    private String user_email;
    private String user_login_gb;
    private String user_input_dt;
    private String user_update_dt;

}
