package com.sample.user.application;

import com.sample.fake.FakeObjectFactory;
import com.sample.user.application.dto.CreateUserRequestDto;
import com.sample.user.application.dto.FollowUserRequestDto;
import com.sample.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRelationServiceTest {
	/**
	 * 첫 번째 유저와 두 번째 유저의 타겟이 다르기 때문에
	 * 기존 유저별로 다르게 한 쌍의 유저의 유효성을 확인하고 넣어야 된다.
	 * <p>
	 * 우리가 필요한건 저장이랑 확인이다.
	 * 테스트 내부에서만 사용되는 레코드를 넣어서 페이크 객체를 만든다.
	 */
	private final UserService userService = FakeObjectFactory.getUserService();
	private final UserRelationService userRelationService = FakeObjectFactory.getUserRelationService();

	private User user1;
	private User user2;

	private FollowUserRequestDto followUserRequestDto;

	@BeforeEach
	void init() {
		CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto("test", "");
		this.user1 = userService.createUser(createUserRequestDto);
		this.user2 = userService.createUser(createUserRequestDto);

		this.followUserRequestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
	}

	@Test
	void givenCreateTwoUser_whenFollow_thenUserFollowSaved() {
		// when
		userRelationService.follow(followUserRequestDto);

		// then
		assertEquals(1, user1.followingCount());
		assertEquals(1, user2.followerCount());
	}

	@Test
	void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError() {
		// given
		userRelationService.follow(followUserRequestDto);

		// when, then
		assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(followUserRequestDto));
	}

	@Test
	void givenCreateOneUser_whenFollow_thenUserThrowError() {
		// given
		FollowUserRequestDto userRequestDto = new FollowUserRequestDto(user1.getId(), user1.getId());

		// when, then
		assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(userRequestDto));
	}

	@Test
	void givenCreateTwoUserFollow_whenUnfollow_thenUserUnFollowSaved() {
		// given
		userRelationService.follow(followUserRequestDto);

		// when
		userRelationService.unfollow(followUserRequestDto);

		// then
		assertEquals(0, user1.followingCount());
		assertEquals(0, user2.followerCount());
	}

	@Test
	void givenCreateTwoUserFollowed_whenUnFollow_thenUserThrowError() {
		// when, then
		assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(followUserRequestDto));
	}

	@Test
	void givenCreateOneUser_whenUnfollow_thenUserThrowError() {
		// given
		FollowUserRequestDto userRequestDto = new FollowUserRequestDto(user1.getId(), user1.getId());

		// when, then
		assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(userRequestDto));
	}
}
