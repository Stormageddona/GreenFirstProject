package com.green.greenfirstproject.auth.principal;

import com.green.greenfirstproject.auth.principal.userinfo.OAuth2UserInfo;
import com.green.greenfirstproject.user.UserMapper;
import com.green.greenfirstproject.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PrincipalOauthDetailService extends DefaultOAuth2UserService
{
    private final UserMapper mapper ;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException
    {
        //"registraionId" 로 어떤 OAuth 로 로그인 했는지 확인 가능(google,naver등)
        System.out.println("getClientRegistration: "+oAuth2UserRequest.getClientRegistration());
        System.out.println("getAccessToken: "+oAuth2UserRequest.getAccessToken().getTokenValue());
        System.out.println("getAttributes: "+ super.loadUser(oAuth2UserRequest).getAttributes());
        //구글 로그인 버튼 클릭 -> 구글 로그인창 -> 로그인 완료 -> code를 리턴(OAuth-Clien라이브러리가 받아줌) -> code를 통해서 AcssToken요청(access토큰 받음)
        // => "userRequest"가 감고 있는 정보
        //회원 프로필을 받아야하는데 여기서 사용되는것이 "loadUser" 함수이다 -> 구글 로 부터 회원 프로필을 받을수 있다.


        /**
         *  OAuth 로그인 회원 가입
         */
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        OAuth2UserInfo oAuth2UserInfo =null;

        if(oAuth2UserRequest.getClientRegistration().getRegistrationId().equals("google")) {
            oAuth2UserInfo = new OAuth2UserInfo(oAuth2User.getAttributes().get("sub").toString(),"google");
        }
        else if(oAuth2UserRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            oAuth2UserInfo = new OAuth2UserInfo(((Map<?, ?>)oAuth2User.getAttributes().get("response")).get("id").toString(), "naver");
        }
        else if(oAuth2UserRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
            oAuth2UserInfo = new OAuth2UserInfo(oAuth2User.getAttributes().get("id").toString(),"kakao");
        }
        else{
            System.out.println("지원하지 않은 로그인 서비스 입니다.");
        }

        String provider = Objects.requireNonNull(oAuth2UserInfo).getProviderName(); //google , naver, facebook etc
        String providerId = oAuth2UserInfo.getId();
        String username = provider + "_" + providerId;
        String password =  "asdasd" ; //중요하지 않음 그냥 패스워드 암호화 하

        User userEntity = mapper.selectUserById(username);
        //처음 서비스를 이용한 회원일 경우
        if(userEntity == null) {
            LocalDateTime createTime = LocalDateTime.now();
            userEntity = User.builder()
                    .id(username)
                    .pwd(password)
                    .role("ROLE_USER")
                    .loginType(oAuth2UserInfo.getProviderId())
                    .build();

            mapper.insertUserData(userEntity);
        }

        return new PrincipalDetail(userEntity, oAuth2User.getAttributes());
    }

}
