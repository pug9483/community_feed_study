package com.sample.user.domain;

import lombok.Getter;

@Getter
public class UserInfo {
	private final String name;
	private final String profileImageUrl;

	public UserInfo(String name, String profileImageUrl) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException();
		}

		this.name = name;
		this.profileImageUrl = profileImageUrl;
	}
}
