package com.luxoft.producer.security.hash;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashUtils {

    @Value("${security.hash.algorithm}")
    private String HASH_ALGORITHM;

    public String hashString(String string) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        return new String(messageDigest.digest(string.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

}
