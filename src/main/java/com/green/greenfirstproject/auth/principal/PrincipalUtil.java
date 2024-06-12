package com.green.greenfirstproject.auth.principal;

import com.green.greenfirstproject.user.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class PrincipalUtil {

    /**
     * 로그인 여부 체크
     * */
    public static Boolean checkLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !auth.getPrincipal().equals("anonymousUser");
    }

    /**
     * 로그인 유저 정보 획득
     * */
    public static PrincipalDetail getPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal().equals("anonymousUser")) {
            return null;
        } else {
            return (PrincipalDetail) auth.getPrincipal();
        }
    }

    public static String getRole()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal().equals("anonymousUser")) return null ;
        PrincipalDetail data = (PrincipalDetail)auth.getPrincipal() ;
        return data.getUser().getRole();
    }

    // 세션 ID 정보 획득
    public static String getSessionId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) auth.getDetails();
        return webAuthenticationDetails.getSessionId();
    }

    // Remote Ip 정보 획득
    public static String getRemoteAddress() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) auth.getDetails();
        return webAuthenticationDetails.getRemoteAddress();
    }

    // 강제 로그아웃
    public static void ForceLogout() {
        SecurityContextHolder.clearContext();
    }

    // user 정보 갱신 (또는 로그인)
    public static void refreshPrincipalDetail(User user, HttpServletRequest request) {
        // 시큐리티 수동 로그인
        PrincipalDetail principal = new PrincipalDetail(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }
}
