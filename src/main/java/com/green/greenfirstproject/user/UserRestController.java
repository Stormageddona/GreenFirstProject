package com.green.greenfirstproject.user;

import com.green.greenfirstproject.auth.principal.PrincipalDetail;
import com.green.greenfirstproject.auth.principal.PrincipalUtil;
import com.green.greenfirstproject.common.EmailService;
import com.green.greenfirstproject.common.dto.Result;
import com.green.greenfirstproject.common.dto.ResultDto;
import com.green.greenfirstproject.common.dto.ResultError;
import com.green.greenfirstproject.user.dto.UserInsertDto;
import com.green.greenfirstproject.user.dto.UserUpdateDto;
import com.green.greenfirstproject.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "유저 로그인" , description = "유저 로그인을 담당하는 API. FormData 형식으로 보내야함.")
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
            "<p> -1 : 실패(의도하지 않은 오류)</p>" +
            "<p> -2 : 이미 가입된 이메일"
    )
    public Result emailCheck(@RequestParam String email)
    {
        try {
            User user = service.getUserByEmail(email) ;

            if (user != null) return ResultError.builder().code(-2).message("이미 가입된 이메일 입니다.").build();
        } catch (NullPointerException ignored) {
        } catch (Exception e) {
            log.error("An error occurred: ", e);
            return ResultError.builder().build();
        }

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
                    "<p> -6 : 토큰 유효기간 초과</p>" +
                    "<p> -7 : 중복된 이메일</p>" +
                    "<p> -8 : 중복된 아이디</p>" +
                    "<p> -9 : 중복된 닉네임</p>"
    )
    public Result postUser(@RequestBody UserInsertDto data)
    {
        if (service.duplicatedData(data.getId(),1)) return ResultError.builder().code(-8).message("중복된 아이디").build();
        if (service.duplicatedData(data.getName(),2)) return ResultError.builder().code(-9).message("중복된 닉네임").build();

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
        } catch (DuplicateKeyException e) {
            return ResultError.builder().code(-7).message("중복된 이메일입니다.").build();
        } catch (Exception e) {
            log.error("An error occurred: ", e);
            return ResultError.builder().build();
        }
        return ResultDto.builder().build();
    }

    @DeleteMapping
    @Operation(summary = "유저 회원탈퇴", description = "실제 탈퇴되는것은 아니고, 상태값을 3으로 변경. 스케줄러로 6개월뒤의 데이터를 삭제할 예정")
    @ApiResponse(description =
            "<p>ResponseCode 응답 코드 </p> " +
                    "<p>  1 : 정상 </p> " +
                    "<p> -1 : 실패(의도하지 않은 오류)</p>" +
                    "<p> -2 : 세션 체크 실패(로그인 정보 없음)</p>"
    )
    public Result deleteUser(@RequestParam Long seq)
    {
        PrincipalDetail data = PrincipalUtil.getPrincipal() ;
        if (data == null) return ResultError.builder().code(-2).message("세션 정보를 확인해 주세요.").build();
        User user = data.getUser() ;
        try {
            service.deleteUser(user.getSeq()) ;

        } catch (Exception e) {
            log.error("An error occurred: ", e);
            return ResultError.builder().build();
        }
        return ResultDto.builder().build();
    }

    @PatchMapping
    @Operation(summary = "유저 정보 수정", description = "")
    @ApiResponse(description =
            "<p>ResponseCode 응답 코드 </p> " +
                    "<p>  1 : 정상 </p> " +
                    "<p> -1 : 실패(의도하지 않은 오류)</p>" +
                    "<p> -2 : 세션 체크 실패(로그인 정보 없음)</p>" +
                    "<p> -3 : 중복된 닉네임</p>"
    )
    public Result patchUser(@RequestBody UserUpdateDto data)
    {
        //유효성 검증
        if (service.duplicatedData(data.getName(),2)) return ResultError.builder().code(-3).message("중복된 닉네임").build();
        PrincipalDetail principal = PrincipalUtil.getPrincipal() ;
        if (principal == null) return ResultError.builder().code(-2).message("세션 정보를 확인해 주세요.").build();
        User user = principal.getUser() ;

        try {
            if(data.getPw() != null)
                if (!data.getPw().equals(data.getPwCheck())) return ResultError.builder().code(-3).message("비밀번호 확인이 실패하였습니다.").build();

            service.updateUser(user,data);
        } catch (Exception e) {
            log.error("An error occurred: ", e);
            return ResultError.builder().build();
        }

        return ResultDto.builder().build();
    }


    @GetMapping("duplicated")
    @Operation(summary = "유저 닉네임, 아이디 중복 확인", description = "중복 확인 메소드.")
    @ApiResponse(description =
            "<p>ResponseCode 응답 코드 </p> " +
                    "<p>  1 : 정상. 중복된 코드가 없음(false) </p> " +
                    "<p>  2 : 정상. 중복된 코드가 있음(true) </p> " +
                    "<p> -1 : 실패(의도하지 않은 오류)</p>"
    )
    public Result duplicatedCheck(
            @Parameter(description = "중복 검증할 스트링") String str
            , @Parameter(description = "1 : 아이디 / 2 : 닉네임 ") Integer type)
    {
        if (!(type == 1 || type == 2)) return ResultError.builder().code(-2).message("타입값 유효성 체크 실패").build();
        try {
            boolean data = service.duplicatedData(str,type) ;
            return ResultDto.<Boolean>builder().code(data ? 2 : 1).message(data ? "이미 존재하는 문자열 입니다." : "존재하지 않는 문자열 입니다.").Data(data).build();
        } catch (Exception e) {
            log.error("An error occurred: ", e);
            return ResultError.builder().build();
        }
    }

}
