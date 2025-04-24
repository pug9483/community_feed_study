package com.sample.user.repository.entity;

import com.sample.common.repository.entity.TimeBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_user_relation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@IdClass(UserRelationIdEntity.class)
public class UserRelationEntity extends TimeBaseEntity {
    @Id
    private Long followingUserId;

    @Id
    private Long followerUserId;
}
