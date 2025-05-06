package com.sample.post.repository.post_queue;

import com.sample.post.application.interfaces.PostRepository;
import com.sample.post.repository.entity.post.PostEntity;
import com.sample.post.repository.entity.post.UserPostQueueEntity;
import com.sample.post.repository.jpa.JpaPostRepository;
import com.sample.post.repository.jpa.JpaUserPostQueueRepository;
import com.sample.user.repository.entity.UserEntity;
import com.sample.user.repository.jpa.JpaUserRelationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {
    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getAuthor();
        List<Long> followersIds = jpaUserRelationRepository.findFollowers(userEntity.getId());

        List<UserPostQueueEntity> userPostQueueEntities = followersIds.stream()
                .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), userEntity.getId()))
                .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<Long> postIds = jpaPostRepository.findAllPostIdsByAuthorId(targetId);

        List<UserPostQueueEntity> userPostQueueEntities = postIds.stream()
                .map(postId -> new UserPostQueueEntity(postId, userId, targetId))
                .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}
