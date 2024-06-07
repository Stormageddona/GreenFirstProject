package com.green.greenfirstproject.user;


import com.green.greenfirstproject.user.model.User;
import com.green.greenfirstproject.user.model.UserEmail;
import com.green.greenfirstproject.user.model.loginUser;
import com.green.greenfirstproject.user.model.postUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int postUser(postUser p);

    User loginUser(String p);

    int  countUserId(String p);

    int  countUserNm(String p);

    int insUserEmail(UserEmail p);

    UserEmail selUserEmail(String p);


}
