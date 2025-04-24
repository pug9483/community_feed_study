package com.sample.user.application.interfaces;

import com.sample.user.domain.User;

public interface UserRelationRepository {
	boolean isAlreadyFollow(User user, User targetUser);
	void save(User user, User targetUser);
	void delete(User user, User targetUser);
}
