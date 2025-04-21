package com.sample.user.domain;

public class User {
	private final Long id;
	private final UserInfo info;
	private final UserRelationCounter followingCounter;
	private final UserRelationCounter followerCounter;

	public User(Long id, UserInfo info) {
		this.id = id;
		this.info = info;
		followingCounter = new UserRelationCounter();
		followerCounter = new UserRelationCounter();
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
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
