package com.sample.user.application;

import com.sample.user.application.dto.CreateUserRequestDto;
import com.sample.user.application.dto.GetUserResponseDto;
import com.sample.user.domain.User;
import com.sample.user.domain.UserInfo;
import com.sample.user.application.interfaces.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User createUser(CreateUserRequestDto dto) {
		UserInfo info = new UserInfo(dto.name(), dto.profileImageUrl());
		User user = new User(null, info);
		return userRepository.save(user);
	}

	public User getUser(Long id) {
		return userRepository.findById(id);
	}

	public GetUserResponseDto getUserProfile(Long id) {
		User user = getUser(id);
		return new GetUserResponseDto(user);
	}
}
