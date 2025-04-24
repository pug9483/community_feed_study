package com.sample.user.repository.jpa;

import com.sample.user.repository.entity.UserRelationEntity;
import com.sample.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
}
