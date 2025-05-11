package com.sample.auth;

import com.sample.auth.domain.Password;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {
    @Test
    void givenPassword_whenMatchSamePassword_thenReturnTrue() {
        Password password = Password.createEncryptedPassword("password");
        assertTrue(password.matchPassword("password"));
    }

    @Test
    void givenPassword_whenMatchDifferentPassword_thenReturnFalse() {
        Password password = Password.createEncryptedPassword("password");
        assertFalse(password.matchPassword("differentPassword"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenPasswordIsNull_thenThrowException(String password) {
        assertThrows(IllegalArgumentException.class, () -> Password.createEncryptedPassword(password));
    }
}
