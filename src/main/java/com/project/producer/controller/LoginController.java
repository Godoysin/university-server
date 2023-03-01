package com.project.producer.controller;

import com.project.producer.security.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("university")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public String login(@RequestParam @NonNull String username, @RequestParam @NonNull String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtUtils.generateAccessToken(username);
        } catch (Exception e) {
            log.error("Error found during user login: {}", e.toString());
            throw new BadCredentialsException("User could not be authenticated");
        }
    }

}
