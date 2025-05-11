package com.sample.auth.domain;

import lombok.Getter;

public class UserAuth {
    private Long userId;
    private final Email email;
    private final Password password;
    private final UserRole userRole;

    public UserAuth(String email, String password, String role) {
        this.email = Email.createEmail(email);
        this.password = Password.createEncryptedPassword(password);
        this.userRole = UserRole.valueOf(role);
    }

    public UserAuth(String email, String password, String userRole, Long userId) {
        this(email, password, userRole);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email.getEmailText();
    }

    public String getPassword() {
        return password.getEncryptedPassword();
    }

    public String getUserRole() {
        return userRole.name();
    }
}
