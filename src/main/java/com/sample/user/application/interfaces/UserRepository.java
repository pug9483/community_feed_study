package com.sample.user.application.interfaces;

import java.util.Optional;

import com.sample.user.domain.User;

public interface UserRepository {
	User save(User user);

	User findById(Long id);
}
