package com.sample.auth.application;

import com.sample.auth.application.dto.CreateUserAuthRequestDto;
import com.sample.auth.application.interfaces.UserAuthRepository;
import com.sample.auth.domain.UserAuth;
import com.sample.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserAuthRepository userAuthRepository;

    public Long registerUser(CreateUserAuthRequestDto dto) {
        UserAuth userAuth = new UserAuth(dto.email(), dto.password(), dto.role());
        User user = new User(dto.name(), dto.profileImageUrl());
        userAuth = userAuthRepository.registerUser(userAuth, user);

        return userAuth.getUserId();
    }
}
