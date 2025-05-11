package com.sample.auth.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Stack;

public class SHA256 {
    private SHA256() {}

    public static String encrypt(String text) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());
            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Failed to encrypt the password");
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }



}
