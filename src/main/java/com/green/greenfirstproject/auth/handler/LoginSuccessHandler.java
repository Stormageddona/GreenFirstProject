package com.green.greenfirstproject.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.greenfirstproject.auth.principal.PrincipalUtil;
import com.green.greenfirstproject.common.dto.Result;
import com.green.greenfirstproject.common.dto.ResultDto;
import com.green.greenfirstproject.common.dto.ResultError;
import com.green.greenfirstproject.user.dto.UserLoginResponse;
import com.green.greenfirstproject.user.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException
    {
        User user = Objects.requireNonNull(PrincipalUtil.getPrincipal()).getUser();
        Result result = null ;
        UserLoginResponse userLoginResponse = new UserLoginResponse(user) ;
        result = switch (user.getGb()) {
            case 1 -> ResultDto.builder().data(userLoginResponse).build();
            case 2 -> ResultError.builder().code(-3).msg("정지된 유저 입니다.").build();
            case 3 -> ResultError.builder().code(-4).msg("탈퇴된 유저 입니다.").build();
            default -> null;
        };

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));


    }

}
