package com.green.greenfirstproject.user;

import com.green.greenfirstproject.common.CustomFileUtils;
import com.green.greenfirstproject.common.ResultDto;
import com.green.greenfirstproject.common.model.CustomException;
import com.green.greenfirstproject.eMail.EMailService;
import com.green.greenfirstproject.user.model.SignInUserRes;
import com.green.greenfirstproject.user.model.UserEmail;
import com.green.greenfirstproject.user.model.loginUser;
import com.green.greenfirstproject.user.model.postUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping("api/user")
@RestController
@RequiredArgsConstructor
@Slf4j

@Tag(name= "유저 쪽 ")
public class UserController {

    private final UserService service;
    private final CustomFileUtils customFileUtils;
    private  final EMailService sendMail;
    @PostMapping("sign-up")
    public ResultDto<Integer> signUp(@RequestBody postUser p) {
        service.selUserEmail(p.getToken());
        UserEmail token = service.selUserEmail(p.getToken());
        if(token == null){
            return ResultDto.resultDto1(-1, "인증번호가 틀렸다.");
        }else if (token.getEndDt().isAfter(LocalDateTime.now())){
            return ResultDto.resultDto1(-2, "인증시간이 초과되었습니다.");
        }

        try {
            int result = service.postUser(p);
            return ResultDto.resultDto(1, "회원가입성공", result);

        } catch (DuplicateKeyException e) {
            return ResultDto.resultDto1(-3, "아이디중복");
        } catch (CustomException e) {
            return ResultDto.resultDto1(-2, "닉네임 중복 ");
        } catch (Exception e) {
            return ResultDto.resultDto1(-1, "알수없는 오류 ^.^ ^<^ ^>^ ");
        }

    }

    @PostMapping("sign-in")
    public ResultDto<SignInUserRes> login(@RequestBody loginUser p) {
        try {
            SignInUserRes result = service.loginUser(p);
            return ResultDto.resultDto(1, "로그인성공", result);
        } catch (DuplicateKeyException e) {
            return ResultDto.resultDto1(-2, "아이디 혹은 비밀번호 틀림");
        }
    }

    public String createNumber(){
        String number = String.valueOf((int)(Math.random() * (90000)) + 100000);// (int) Math.random() * (최댓값-최소값+1) + 최소값
        return  number;
    }
    @PostMapping("e-Mail")
    public String sendEmail(@RequestParam String email) {
        String num = createNumber();
        UserEmail p = new UserEmail(email,num);
        try {
            String title = "이메일 인증코드";
            String contents = "인증번호는 " + num + "입니다";
            sendMail.sendMail(email, title, contents);
            int res = service.insUserEmail(p);

        } catch (MessagingException e) {
            e.printStackTrace();
            return "이메일 발송 실패";
        }
            return "이메일 을 보냄 ";
    }

}
