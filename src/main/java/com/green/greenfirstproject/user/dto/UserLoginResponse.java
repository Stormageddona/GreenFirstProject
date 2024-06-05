package com.green.greenfirstproject.user.dto;

import com.green.greenfirstproject.common.exception.DataWrongException;
import com.green.greenfirstproject.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse
{
    private String userId ;
    private String userName ;
    private String loginType ;

    public UserLoginResponse(User user)
    {
        this.userId = user.getId();
        this.userName = user.getName();
        System.out.println(user.getLoginType());
        switch (user.getLoginType())
        {
            case 1 : this.loginType = "email"; break;
            case 2 : this.loginType = "kakao"; break;
            case 3 : this.loginType = "naver"; break;
            case 4 : this.loginType = "google"; break;
            default: this.loginType = null ;
        }
    }
}
