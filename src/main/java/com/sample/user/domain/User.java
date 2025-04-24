package com.sample.user.domain;

import java.util.Objects;

import com.sample.common.domain.PositiveIntegerCounter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor
public class User {
	private final Long id;
	private final UserInfo info;
	private final PositiveIntegerCounter followingCounter;
	private final PositiveIntegerCounter followerCounter;

	public User(Long id, UserInfo userInfo) {
		if (userInfo == null) {
			throw new IllegalArgumentException();
		}

		this.id = id;
		this.info = userInfo;
		followingCounter = new PositiveIntegerCounter();
		followerCounter = new PositiveIntegerCounter();
	}

	public void follow(User targetUser) {
		if (this.equals(targetUser)) {
			throw new IllegalArgumentException();
		}

		followingCounter.increase();
		targetUser.increaseFollowerCount();
	}

	public void unfollow(User targetUser) {
		if (this.equals(targetUser)) {
			throw new IllegalArgumentException();
		}

		followingCounter.decrease();
		targetUser.decreaseFollowerCount();
	}

	/**
	 * 디미터 법칙을 만족하기 위해 private 메서드로 추출한다.
	 */
	private void increaseFollowerCount() {
		followerCounter.increase();
	}

	private void decreaseFollowerCount() {
		followerCounter.decrease();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof User user)) {
			return false;
		}
		return Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	public int followerCount() {
		return followerCounter.getCount();
	}

	public int followingCount() {
		return followingCounter.getCount();
	}

	public String getProfileImage() {
		return info.getProfileImageUrl();
	}

	public String getName() {
		return info.getName();
	}
}
