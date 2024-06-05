package com.green.greenfirstproject.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.greenfirstproject.auth.principal.PrincipalUtil;
import com.green.greenfirstproject.common.dto.ResultDto;
import com.green.greenfirstproject.common.dto.ResultError;
import com.green.greenfirstproject.user.dto.UserLoginResponse;
import com.green.greenfirstproject.user.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class LoginFailedHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
                                        final AuthenticationException exception) throws IOException, ServletException
    {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(ResultError.builder().code(-2).message("아이디나 비밀번호가 일치하지 않습니다.").build()));
    }
}
