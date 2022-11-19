package com.saif.foodmanagement.utils;

import java.security.MessageDigest;

/**
 * @author saifuzzaman
 */
public class HashGenerationUtil {

    public static String getSha512(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            byte[] bytes = messageDigest.digest(password.getBytes());
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            String hashedPassword = stringBuilder.toString();

            return hashedPassword;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
