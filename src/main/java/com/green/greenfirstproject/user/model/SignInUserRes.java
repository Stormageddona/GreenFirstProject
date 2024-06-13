package com.green.greenfirstproject.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignInUserRes {
    @JsonIgnore
    private long user_seq;

    private String userId;
    private String userNm;
    private String user_email;
}
