package com.green.greenfirstproject.user;

import com.green.greenfirstproject.user.dto.UserInsertDto;
import com.green.greenfirstproject.user.model.EmailToken;
import com.green.greenfirstproject.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectUserById(String id) ;
    User selectUserByEmail(String email) ;
    List<User> selectAllUsers() ;

    void insertUserData(User user); ;
    void deleteUserData(Long userId); ;
    void updateUserData(User user); ;

    void insertEmailToken(EmailToken data) ;
    void deleteEmailToken(String email); ;
    EmailToken selectToken(String token) ;

    boolean existsByUserName(String str) ;
    boolean existsById(String str) ;
}
