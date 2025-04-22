package com.sample.user.interfaces;

import java.util.Optional;

import com.sample.user.domain.User;

public interface UserRepository {
	User save(User user);

	Optional<User> findById(Long id);
}
