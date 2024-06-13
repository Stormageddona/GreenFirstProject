package com.green.greenfirstproject.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class postUser {
    @JsonIgnore
    private long user_seq;
    @Schema(defaultValue = "qwer1")
    private String userId;
    @Schema(defaultValue = "qwer1")
    private String userPw;
    @Schema(defaultValue = "헤헤")
    private String userNm;
    @Schema(defaultValue = "dkdkdk@naver.com")
    private String userEmail;

    private String token;

}
