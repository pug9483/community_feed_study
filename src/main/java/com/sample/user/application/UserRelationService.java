package com.sample.user.application;

import com.sample.user.application.dto.FollowUserRequestDto;
import com.sample.user.domain.User;
import com.sample.user.application.interfaces.UserRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class UserRelationService {
	private final UserService userService;
	private final UserRelationRepository userRelationRepository;

	public UserRelationService(UserService userService, UserRelationRepository userRelationRepository) {
		this.userService = userService;
		this.userRelationRepository = userRelationRepository;
	}

	public void follow(FollowUserRequestDto dto) {
		User user = userService.getUser(dto.userId());
		User targetUser = userService.getUser(dto.targetUserId());

		if (userRelationRepository.isAlreadyFollow(user, targetUser)) {
			throw new IllegalArgumentException();
		}

		user.follow(targetUser);
		userRelationRepository.save(user, targetUser);
	}

	public void unfollow(FollowUserRequestDto dto) {
		User user = userService.getUser(dto.userId());
		User targetUser = userService.getUser(dto.targetUserId());

		if (!userRelationRepository.isAlreadyFollow(user, targetUser)) {
			throw new IllegalArgumentException();
		}

		user.unfollow(targetUser);
		userRelationRepository.delete(user, targetUser);
	}
}
