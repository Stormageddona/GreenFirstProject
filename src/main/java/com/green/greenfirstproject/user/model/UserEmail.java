package com.green.greenfirstproject.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter


public class UserEmail {
    private String emailId;
    private String emailDt;
    private  LocalDateTime inputDt;
    private LocalDateTime endDt;

    public UserEmail(String emailDt, String emailId) {
        this.emailDt = emailDt;
        this.emailId = emailId;
        this.endDt = LocalDateTime.now().plusMinutes(30);
    }


}
