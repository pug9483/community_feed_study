package com.sample.user.interfaces;

import com.sample.user.domain.User;

public interface UserRelationRepository {
	boolean isAlreadyFollow(User user, User targetUser);
	void save(User user, User targetUser);
	void delete(User user, User targetUser);
}
