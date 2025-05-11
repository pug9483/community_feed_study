package com.sample.auth.application;

import com.sample.auth.application.dto.SendEmailRequestDto;
import com.sample.auth.application.interfaces.EmailSendRepository;
import com.sample.auth.application.interfaces.EmailVerificationRepository;
import com.sample.auth.domain.Email;
import com.sample.auth.domain.RandomTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailSendRepository emailSendRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public void sendEmail(SendEmailRequestDto dto) {
        Email email = Email.createEmail(dto.email());
        String token = RandomTokenGenerator.generateToken();

        emailSendRepository.sendEmail(email, token);
        emailVerificationRepository.createEmailVerification(email, token);
    }

    public void verifyEmail(String email, String token) {
    }
}
