package com.green.greenfirstproject.auth.principal.userinfo;

import lombok.Getter;

import java.util.Map;
import java.util.Objects;

@Getter
public class OAuth2UserInfo
{
    private final String id ;
    private final String providerName ;
    private final Integer providerId ;

    public OAuth2UserInfo(String id, String providerName)
    {
        this.id = id ;
        this.providerName = providerName ;
        switch (providerName)
        {
            case "google": this.providerId = 2; break;
            case "kakao": this.providerId = 3; break;
            case "naver": this.providerId = 4; break;
            default: this.providerId = null; break;
        }
    }


}
