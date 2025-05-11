package com.sample.auth.repository;

import com.sample.auth.application.interfaces.EmailVerificationRepository;
import com.sample.auth.domain.Email;
import com.sample.auth.repository.entity.EmailVerificationEntity;
import com.sample.auth.repository.jpa.JpaEmailVerificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {
    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Override
    @Transactional
    public void createEmailVerification(Email email, String token) {
        String emailAddress = email.getEmailText();
        Optional<EmailVerificationEntity> entity = jpaEmailVerificationRepository.findByEmail(emailAddress);

        if(entity.isPresent()) {
            EmailVerificationEntity emailVerificationEntity = entity.get();
            if (emailVerificationEntity.isVerified()) {
                throw new IllegalStateException("Email is already verified");
            }

            emailVerificationEntity.updateToken(token);
            return;
        }

        EmailVerificationEntity emailVerificationEntity = new EmailVerificationEntity(emailAddress, token);
        jpaEmailVerificationRepository.save(emailVerificationEntity);
    }

    @Override
    @Transactional
    public void verifyEmail(Email email, String token) {
        String emailText = email.getEmailText();

        EmailVerificationEntity entity = jpaEmailVerificationRepository.findByEmail(emailText)
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));

        if(entity.isVerified()) {
            throw new IllegalStateException("Email is already verified");
        }

        if(!entity.hasSameToken(token)) {
            throw new IllegalStateException("Email token does not match");
        }

        entity.verify();
    }

    @Override
    public boolean isEmailVerified(Email email) {
        EmailVerificationEntity entity = jpaEmailVerificationRepository.findByEmail(email.getEmailText())
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));
        return entity.isVerified();
    }
}
