package com.luxoft.producer.security;

import com.luxoft.producer.controller.filter.JwtValidatorFilter;
import com.luxoft.producer.security.constants.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration {

    private final JwtValidatorFilter jwtValidatorFilter;

    private final RequestExceptionHandler requestExceptionHandler;

    @Autowired
    public SecurityConfiguration(JwtValidatorFilter jwtValidatorFilter, RequestExceptionHandler requestExceptionHandler) {
        this.jwtValidatorFilter = jwtValidatorFilter;
        this.requestExceptionHandler = requestExceptionHandler;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() {
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                if (rawPassword == null) {
                    throw new IllegalArgumentException("rawPassword cannot be null");
                }
                if (encodedPassword == null || encodedPassword.length() == 0) {
                    return false;
                }
                return (rawPassword.toString().compareTo(encodedPassword.toString()) == 0);
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
                .addFilterBefore(jwtValidatorFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
//                    .requestMatchers("/university/teachers/teacher-list", "/university/teachers/contact-request").authenticated()
                    .requestMatchers("/university/teachers/teacher-list").authenticated()
                    .requestMatchers("/university/teachers/contact-request").hasAuthority(RoleEnum.ROLE.getRole())
                    .requestMatchers("/university/login", "/university/careers/career-list").permitAll()
                    .requestMatchers("/university/users/new-user").authenticated()
                    .and()
                .exceptionHandling().authenticationEntryPoint(requestExceptionHandler).and()
                .httpBasic()
                ;
        return httpSecurity.build();
    }

}
