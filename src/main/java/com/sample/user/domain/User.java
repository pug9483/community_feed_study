package com.sample.user.domain;

public class User {
	private final Long id;
	private final UserInfo info;

	public User(Long id, UserInfo info) {
		this.id = id;
		this.info = info;
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
