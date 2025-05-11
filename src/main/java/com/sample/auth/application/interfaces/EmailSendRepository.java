package com.sample.auth.application.interfaces;

import com.sample.auth.domain.Email;

public interface EmailSendRepository {
    void sendEmail(Email email, String randomToken);
}
