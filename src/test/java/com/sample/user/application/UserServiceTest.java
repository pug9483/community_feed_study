package com.sample.user.application;

import static org.junit.jupiter.api.Assertions.*;

import com.sample.fake.FakeObjectFactory;
import org.junit.jupiter.api.Test;

import com.sample.user.application.dto.CreateUserRequestDto;
import com.sample.user.domain.User;
import com.sample.user.domain.UserInfo;
import com.sample.user.interfaces.UserRepository;
import com.sample.user.repository.FakeUserRepository;

class UserServiceTest {
	private final UserService userService = FakeObjectFactory.getUserService();

	@Test
	void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
		// given
		CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

		// when
		User savedUser = userService.createUser(dto);

		// then
		User findUser = userService.getUser(savedUser.getId());
		UserInfo userInfo = findUser.getInfo();
		assertEquals(findUser.getId(), savedUser.getId());
		assertEquals("test", userInfo.getName());
	}
}
