package com.example.awswithspringproject.springboot.config.auth;

import com.example.awswithspringproject.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;

//@RequiredArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final CustomOAuth2UserService customOAuth2UserService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .headers().frameOptions().disable()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
//                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
//                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .and()
//                .oauth2Login()
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService);
//    }
//}

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/", "/css/", "images/", "/js/", "/h2/", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

        return http.build();
    }
}