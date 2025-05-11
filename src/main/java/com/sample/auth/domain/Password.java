package com.sample.auth.domain;

public class Password {
    private final String encryptedPassword;

    private Password(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public static Password createEncryptedPassword(String password) {
        if(password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return new Password(SHA256.encrypt(password));
    }

    public boolean matchPassword(String password) {
        return encryptedPassword.matches(SHA256.encrypt(password));
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }
}
