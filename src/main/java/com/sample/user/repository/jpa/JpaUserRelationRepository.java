package com.sample.user.repository.jpa;

import com.sample.user.repository.entity.UserRelationEntity;
import com.sample.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
    @Query("SELECT u.followingUserId FROM UserRelationEntity u WHERE u.followingUserId = :userId")
    List<Long> findFollowers(Long userId);
}
