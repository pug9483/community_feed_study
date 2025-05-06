package com.sample.user.repository;

import com.sample.post.repository.post_queue.UserPostQueueCommandRepository;
import com.sample.user.application.interfaces.UserRelationRepository;
import com.sample.user.domain.User;
import com.sample.user.repository.entity.UserEntity;
import com.sample.user.repository.entity.UserRelationEntity;
import com.sample.user.repository.entity.UserRelationIdEntity;
import com.sample.user.repository.jpa.JpaUserRelationRepository;
import com.sample.user.repository.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;
    private final UserPostQueueCommandRepository commandRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity userRelationIdEntity = new UserRelationIdEntity(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(userRelationIdEntity);
    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity userRelationEntity = new UserRelationEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.save(userRelationEntity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        commandRepository.saveFollowPost(user.getId(), targetUser.getId());
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationIdEntity userRelationIdEntity = new UserRelationIdEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.deleteById(userRelationIdEntity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        commandRepository.deleteUnfollowPost(user.getId(), targetUser.getId());
    }
}
