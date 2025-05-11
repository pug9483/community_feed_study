package com.sample.auth.repository;

import com.sample.auth.application.interfaces.EmailSendRepository;
import com.sample.auth.domain.Email;
import org.springframework.stereotype.Repository;

@Repository
public class EmailSendRepositoryImpl implements EmailSendRepository {
    @Override
    public void sendEmail(Email email, String randomToken) {
        // TODO: Implement the logic to send an email
    }
}
