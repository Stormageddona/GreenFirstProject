package com.green.greenfirstproject.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserInsertDto {
    @JsonIgnore
    private Long seq ;
    @Schema(description = "아이디")
    private String id ;
    @Schema(description = "패스워드 (영어, 숫자, !@#$%^&*의 특수문자만 통과, 8~20자사이만 통과 ,영어와 숫자가 최소 1개씩 들어가야만 통과)")
    private String pw ;
    @Schema(description = "패스워드 확인")
    private String pwCheck ;
    @Schema(description = "이름")
    private String name ;
    @JsonIgnore
    @Schema(description = "이메일")
    private String email ;
    @Schema(description = "토큰")
    private String token ;
    @JsonIgnore
    private Integer loginType ;

}
