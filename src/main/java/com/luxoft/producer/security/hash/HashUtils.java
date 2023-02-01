package com.luxoft.producer.security.hash;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashUtils {

    public static String hashString(String string) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        return new String(messageDigest.digest(string.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

}
