package com.sample.auth.application;

import com.sample.auth.application.dto.CreateUserAuthRequestDto;
import com.sample.auth.application.interfaces.EmailVerificationRepository;
import com.sample.auth.application.interfaces.UserAuthRepository;
import com.sample.auth.domain.Email;
import com.sample.auth.domain.UserAuth;
import com.sample.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserAuthRepository userAuthRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public Long registerUser(CreateUserAuthRequestDto dto) {
        Email email = Email.createEmail(dto.email());
        if(!emailVerificationRepository.isEmailVerified(email)) {
            throw new IllegalArgumentException("Email not verified");
        }

        UserAuth userAuth = new UserAuth(dto.email(), dto.password(), dto.role());
        User user = new User(dto.name(), dto.profileImageUrl());
        userAuth = userAuthRepository.registerUser(userAuth, user);

        return userAuth.getUserId();
    }
}
