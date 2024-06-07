package com.green.greenfirstproject.user;

import com.green.greenfirstproject.user.dto.UserInsertDto;
import com.green.greenfirstproject.user.dto.UserUpdateDto;
import com.green.greenfirstproject.user.model.EmailToken;
import com.green.greenfirstproject.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.BreakIterator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper mapper ;
    private final PasswordEncoder passwordEncoder;

    public User insertUser(UserInsertDto data) throws Exception
    {
        data.setPw(passwordEncoder.encode(data.getPw())) ;
        User user = new User(data) ;
        mapper.insertUserData(user) ;
        return user ;
    }

    public void deleteUser(Long seq) throws Exception
    {
        mapper.deleteUserData(seq);

    }

    public void updateUser(User user, UserUpdateDto dto) throws Exception
    {
        System.out.println(user);
        System.out.println(dto);
        if (!(dto.getPw() == null || dto.getPw().isEmpty())) dto.setPw(passwordEncoder.encode(dto.getPw()));
        user.userDataChange(dto);
        mapper.updateUserData(user);
    }

    public boolean duplicatedData(String str, Integer type)
    {
        return switch (type) {
            case 1 -> mapper.existsById(str) ;
            case 2 -> mapper.existsByUserName(str) ;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        } ;
    }

    public List<User> getAllUsers() throws Exception
    {
        return mapper.selectAllUsers() ;
    }

    public User getUserById(String id) throws Exception
    {
        User user = mapper.selectUserById(id) ;
        if (user == null) throw new NullPointerException("유저 정보가 존재하지 않습니다.") ;
        return user ;
    }

    public User getUserByEmail(String email) throws Exception
    {
        User user = mapper.selectUserByEmail(email) ;
        if (user == null) throw new NullPointerException("유저 정보가 존재하지 않습니다.") ;
        return user ;
    }

    public void insertEmailToken(String email, String code) throws Exception
    {
        EmailToken emailToken = new EmailToken(email, code) ;
        mapper.insertEmailToken(emailToken);
    }

    public void deleteEmailToken(String email) throws  Exception
    {
        mapper.deleteEmailToken(email) ;
    }

    public String checkEmailToken(String code) throws Exception
    {
        EmailToken token = mapper.selectToken(code) ;
        LocalDateTime now = LocalDateTime.now() ;
        if (token == null)
            throw new NullPointerException() ;
        if (now.isAfter(token.getEndDt()))
            throw new TimeoutException() ;
        return token.getEmail() ;
    }

    public User socialUserJoin(String id, Integer type) throws Exception
    {
        UserInsertDto dto = new UserInsertDto() ;
        dto.setId(id) ;
        dto.setName(id);
        dto.setPw(passwordEncoder.encode("test")) ;
        dto.setEmail(id);
        dto.setLoginType(type);

        return insertUser(dto) ;



    }

}
