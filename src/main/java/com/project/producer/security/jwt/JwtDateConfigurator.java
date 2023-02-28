package com.project.producer.security.jwt;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtDateConfigurator {

    private static final long EXPIRE_DURATION = 1000L * 60L * 60L * 24L * 30L; // 30 days

    public Date generateDate() {
        return new Date();
    }

    public long getExpireDuration() {
        return EXPIRE_DURATION;
    }

}
