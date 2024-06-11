package com.green.greenfirstproject.user;

import com.green.greenfirstproject.auth.principal.PrincipalUtil;
import com.green.greenfirstproject.common.dto.Result;
import com.green.greenfirstproject.common.dto.ResultDto;
import com.green.greenfirstproject.common.dto.ResultError;
import com.green.greenfirstproject.user.dto.UserLoginResponse;
import com.green.greenfirstproject.user.model.User;
import com.green.greenfirstproject.user.socialservice.LoginServiceForGoogle;
import com.green.greenfirstproject.user.socialservice.LoginServiceForKakao;
import com.green.greenfirstproject.user.socialservice.LoginServiceForNaver;
import io.swagger.v3.oas.annotations.Hidden;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class SocialLoginController
{
    private final LoginServiceForGoogle googleService ;
    private final LoginServiceForKakao kakaoService ;
    private final LoginServiceForNaver naverService ;


    @Hidden
    @GetMapping("kakao")
    //받은 엑세스 토큰을 처리후 모든 session에 전송하는 컨트롤러
    public String AuthKakaoLogin(String code, HttpServletRequest request, Model model) throws Exception {
        //받은 정보 처리.
        User user = kakaoService.clientKakaoOauth(code) ;
        // null일시 정상처리되지않음.
        if (user == null) {
            model.addAttribute("userdata",ResultError.builder().build()) ;
            return "close";
        }
        PrincipalUtil.refreshPrincipalDetail(user,request);

        UserLoginResponse login = new UserLoginResponse(user) ;
        model.addAttribute("userdata",ResultDto.<UserLoginResponse>builder().data(login).build()) ;
        return "close";
    }


    @Hidden
    @GetMapping("naver")
    //받은 엑세스 토큰을 처리후 모든 session에 전송하는 컨트롤러
    public String AuthNaverLogin(String code, String state, HttpServletRequest request,Model model) throws Exception {

        //받은 정보 처리.
        User user = naverService.clientNaverOauth(code,state) ;
        // null일시 정상처리되지않음.
        if (user == null) {
            model.addAttribute("userdata",ResultError.builder().build()) ;
            return "close";
        }
        PrincipalUtil.refreshPrincipalDetail(user,request);

        UserLoginResponse login = new UserLoginResponse(user) ;
        model.addAttribute("userdata",ResultDto.<UserLoginResponse>builder().data(login).build()) ;
        return "close";    }

    @Hidden
    @GetMapping("google")
    //받은 엑세스 토큰을 처리후 모든 session에 전송하는 컨트롤러
    public String AuthGoogleLogin(String code, HttpServletRequest request, Model model) throws Exception {

        //받은 정보 처리.
        User user = googleService.clientGoogleOauth(code) ;
        System.out.println(user);
        // null일시 정상처리되지않음.
        if (user == null) {
            model.addAttribute("userdata",ResultError.builder().build()) ;
            return "close";
        }

        PrincipalUtil.refreshPrincipalDetail(user,request);

        UserLoginResponse login = new UserLoginResponse(user) ;
        model.addAttribute("userdata",ResultDto.<UserLoginResponse>builder().data(login).build()) ;
        return "close";
    }

}
