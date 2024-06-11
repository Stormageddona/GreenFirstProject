package com.green.greenfirstproject.user;

import com.green.greenfirstproject.common.CustomFileUtils;
import com.green.greenfirstproject.common.model.CustomException;
import com.green.greenfirstproject.user.model.*;
import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.DuplicateKeyException;


import org.springframework.mail.javamail.JavaMailSenderImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
//    private  final JavaMailSenderImpl mss;
    private final UserMapper mapper;
    private final CustomFileUtils customFileUtils;

    private JavaMailSenderImpl emailSender;

    @Transactional
    @Operation(summary = "회원가입을 할 수 있습니다 ", description = "회원가입을 할 수 있다.")
    public int postUser(postUser p){
        String password = BCrypt.hashpw(p.getUserPw(),BCrypt.gensalt());
        p.setUserPw(password);
        User userinfo = new User();
        int userIdCheck = mapper.countUserId(p.getUserId());
        int userNameCheck = mapper.countUserNm(p.getUserNm());
        if(userIdCheck >= 1 ){
            throw new DuplicateKeyException("아이디 중복 ");
        }else if(userNameCheck >= 1){
            throw new CustomException("닉네임 중복");
        }
        return mapper.postUser(p);
    }
    @Transactional
    public SignInUserRes loginUser( loginUser p){
        User user =mapper.loginUser(p.getUserId());

        if(user == null){
         throw new DuplicateKeyException("아이디혹은 비밀번호 확인");
        }else if(!BCrypt.checkpw(p.getUserPw(),user.getUserPw())){
            throw new DuplicateKeyException("아이디혹은 비밀번호 확인");
        }
        return SignInUserRes.builder()
                .userId(user.getUserId())
                .userNm(user.getUser_nm())
                .user_seq(user.getUser_seq()).
                build();
    }

    public int insUserEmail(UserEmail p){
        return mapper.insUserEmail(p);
    }
    public UserEmail selUserEmail(String p){
        return mapper.selUserEmail(p);
    }
}
