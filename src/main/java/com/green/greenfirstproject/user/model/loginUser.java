package com.green.greenfirstproject.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class loginUser {
    private String userId;

    private String userPw;
}
