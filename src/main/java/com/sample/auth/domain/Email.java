package com.sample.auth.domain;

import java.util.regex.Pattern;

public class Email {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private final String emailText;

    private Email(String email) {
        this.emailText = email;
    }

    public static Email createEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }

        if(isNotValidEmailString(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        return new Email(email);
    }

    public String getEmailText() {
        return this.emailText;
    }

    private static boolean isNotValidEmailString(String email) {
        return !pattern.matcher(email).matches();
    }
}
