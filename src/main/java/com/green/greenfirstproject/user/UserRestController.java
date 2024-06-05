package com.green.greenfirstproject.user;

import com.green.greenfirstproject.auth.principal.PrincipalOauthDetailService;
import com.green.greenfirstproject.common.EmailService;
import com.green.greenfirstproject.common.dto.Result;
import com.green.greenfirstproject.common.dto.ResultDto;
import com.green.greenfirstproject.common.dto.ResultError;
import com.green.greenfirstproject.user.dto.UserInsertDto;
import com.green.greenfirstproject.user.dto.UserLoginData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
@Tag(name = "유저 정보 컨트롤러")
public class UserRestController {

    private final UserService service;

    private final EmailService emailService;


    @PostMapping("login")
    @Operation(summary = "유저 로그인" , description = "유저 로그인을 담당하는 API")
    @ApiResponse(description =
            "<p>ResponseCode 응답 코드 </p> " +
                    "<p>  1 : 정상 </p> " +
                    "<p> -1 : 실패(의도하지 않은 오류)</p>" +
                    "<p> -2 : 로그인 정보 조회 실패</p>" +
                    "<p> -3 : 정지된 유저</p>" +
                    "<p> -4 : 탈퇴된 유저</p>"
    )
    public Result loginUser(@RequestParam String id, @RequestParam String pwd)
    {
        //실제 로그인은 Spring Security 에서 인터셉트하여 진행.
        return null ;
    }

    @GetMapping("auth/email")
    @Operation(summary = "유저 회원가입 이메일 인증 절차", description = "유저 회원가입을 위한 이메일 체크용 인증 절차.")
    @ApiResponse(description =
            "<p>ResponseCode 응답 코드 </p> " +
            "<p>  1 : 정상 </p> " +
            "<p> -1 : 실패(의도하지 않은 오류)</p>"
    )
    public Result emailCheck(@RequestParam String email)
    {
        String code = UUID.randomUUID().toString() ;
        String title = "[프로젝트 이메일 인증]" ;
        String content = "이메일 인증 코드 : " + code ;

        try {
            emailService.sendEmail(email,title, content, "project@gmail.com", "프로젝트 명");
            service.deleteEmailToken(email);
            service.insertEmailToken(email,code);

        } catch (Exception e) {
            log.error("An error occurred: ", e);
            return ResultError.builder().build();
        }
        return ResultDto.builder().build();
    }

    @PostMapping("sign_up")
    @Operation(summary = "유저 회원가입", description = "유저 회원가입 API. 링크로 첨부된 Token 값을 같이 보내야함.")
    @ApiResponse(description =
            "<p>ResponseCode 응답 코드 </p> " +
                    "<p>  1 : 정상 </p> " +
                    "<p> -1 : 실패(의도하지 않은 오류)</p>" +
                    "<p> -2 : 아이디 검증 실패</p>" +
                    "<p> -3 : 비밀번호 확인 실패</p>" +
                    "<p> -4 : 비밀번호 검증 실패</p>" +
                    "<p> -5 : 토큰 검증 실패</p>" +
                    "<p> -6 : 토큰 유효기간 초과</p>"
    )
    public Result postUser(@RequestBody UserInsertDto data)
    {
        //비밀번호 확인
        if (!data.getPw().equals(data.getPwCheck()))
            return ResultError.builder().code(-3).message("비밀번호 확인이 실패하였습니다.").build();
        //아이디 유효성 검증(영어와 숫자만 통과, 4~20자만 통과)
        String regex = "^[A-Za-z0-9]{4,20}$" ;
        if (!data.getId().matches(regex))
            return ResultError.builder().code(-2).message("아이디 검증에 실패하였습니다.").build();
        //비밀번호 검증(영어, 숫자, !@#$%^&*의 특수문자만 통과, 8~20자사이만 통과 ,영어와 숫자가 최소 1개씩 들어가야만 통과
        regex = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9!@#$%^&*]{8,20}$" ;
        if (!data.getPw().matches(regex))
            return ResultError.builder().code(-4).message("비밀번호 검증에 실패하였습니다.").build();
        //토큰 검증
        try {
            data.setEmail(service.checkEmailToken(data.getToken()));
        } catch (NullPointerException e) {
            return ResultError.builder().code(-5).message("토큰 정보를 찾을수 없습니다.").build();
        } catch (TimeoutException e) {
            return ResultError.builder().code(-6).message("토큰의 유효기간이 초과했습니다.(30분)").build();
        } catch (Exception e) {
            log.error("An error occurred: ", e);
            return ResultError.builder().build();
        }
        //실제 로직 진입
        try {
            data.setLoginType(1);
            service.insertUser(data);

        } catch (Exception e) {
            log.error("An error occurred: ", e);
            return ResultError.builder().build();
        }
        return ResultDto.builder().build();
    }


}
