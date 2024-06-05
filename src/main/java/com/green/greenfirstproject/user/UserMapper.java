package com.green.greenfirstproject.user;

import com.green.greenfirstproject.user.dto.UserInsertDto;
import com.green.greenfirstproject.user.model.EmailToken;
import com.green.greenfirstproject.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectUserById(String id) ;

    List<User> selectAllUsers() ;

    void insertUserData(User user); ;
    void deleteUserData(Long userId); ;
    void updateUserData(UserInsertDto user); ;

    void insertEmailToken(EmailToken data) ;
    void deleteEmailToken(String email); ;
    EmailToken selectToken(String token) ;
}
