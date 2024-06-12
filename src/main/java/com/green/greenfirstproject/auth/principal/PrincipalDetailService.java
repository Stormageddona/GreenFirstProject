package com.green.greenfirstproject.auth.principal;

import com.green.greenfirstproject.user.UserMapper;
import com.green.greenfirstproject.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {


    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        System.out.println(id);
        User principal = mapper.selectUserById(id);
        if (principal == null) {
            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + id);
        }
        return new PrincipalDetail(principal);
    }
}
