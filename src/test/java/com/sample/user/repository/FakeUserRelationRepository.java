package com.sample.user.repository;

import java.util.HashSet;
import java.util.Set;

import com.sample.user.domain.User;
import com.sample.user.application.interfaces.UserRelationRepository;

public class FakeUserRelationRepository implements UserRelationRepository {
	private final Set<Relation> store = new HashSet<>();

	@Override
	public boolean isAlreadyFollow(User user, User targetUser) {
		return store.contains(new Relation(user.getId(), targetUser.getId()));
	}

	@Override
	public void save(User user, User targetUser) {
		store.add(new Relation(user.getId(), targetUser.getId()));
	}

	@Override
	public void delete(User user, User targetUser) {
		store.remove(new Relation(user.getId(), targetUser.getId()));
	}
}

record Relation(Long userId, Long targetUserId) {
}
