package com.green.greenfirstproject.auth;

import com.green.greenfirstproject.auth.handler.LoginFailedHandler;
import com.green.greenfirstproject.auth.handler.LoginSuccessHandler;
import com.green.greenfirstproject.auth.principal.PrincipalDetailService;
import com.green.greenfirstproject.auth.principal.PrincipalOauthDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final DataSource dataSource;
    private final LoginSuccessHandler loginSuccessHandler ;
    private final LoginFailedHandler loginFailedHandler ;
    private final PrincipalDetailService principalDetailService ;
    private final PrincipalOauthDetailService principalOauthDetailService;


    @Bean
    PasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }



    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/swagger").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/**").permitAll()
                        .anyRequest().hasAnyRole("USER", "ADMIN")
                )
                .formLogin(
                        login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/api/user/login")
                        .usernameParameter("id")
                        .passwordParameter("pwd")
                        .successHandler(loginSuccessHandler)
                        .failureHandler(loginFailedHandler)
                )
                .oauth2Login(
                        login -> login
                                .loginPage("/login")
                                .loginProcessingUrl("/api/user/social")
                                .defaultSuccessUrl("/")
                                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(principalOauthDetailService))
                )
                .exceptionHandling(exception -> exception.accessDeniedPage("/"))
                .rememberMe(rememberMe -> rememberMe
                        .userDetailsService(principalDetailService)
                        .tokenRepository(tokenRepository())
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                );
        return http.build();

    }

}
