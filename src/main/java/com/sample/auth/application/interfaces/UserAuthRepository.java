package com.sample.auth.application.interfaces;

import com.sample.auth.domain.UserAuth;
import com.sample.user.domain.User;

public interface UserAuthRepository {
    UserAuth registerUser(UserAuth userAuth, User user);
}
