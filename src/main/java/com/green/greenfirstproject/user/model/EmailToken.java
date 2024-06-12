package com.green.greenfirstproject.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailToken {
    private String email ;
    private String token ;
    private LocalDateTime inputDt ;
    private LocalDateTime endDt ;

    public EmailToken(String email, String token)
    {
        this.email = email;
        this.token = token;
        this.inputDt = LocalDateTime.now();
        this.endDt = inputDt.plusMinutes(30);
    }
}
