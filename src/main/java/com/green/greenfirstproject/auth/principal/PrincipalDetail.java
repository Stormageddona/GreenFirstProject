package com.green.greenfirstproject.auth.principal;

import com.green.greenfirstproject.user.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Setter
@Getter
public class PrincipalDetail implements UserDetails, OAuth2User
{

    private User user ;
    private Map<String,Object> attributes;

    //일반 로그인
    public PrincipalDetail(User user)
    {
        this.user = user;
    }

    //Oauth2 로그인
    public PrincipalDetail(User user, Map<String,Object> attributes)
    {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Serial
            private static final long serialVersionUID = 1L;

            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPwd();
    }

    @Override
    public String getUsername() {
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName()
    {
        return user.getName();
    }
}
