package com.sample.post.repository.jpa;

import com.sample.post.repository.entity.like.LikeEntity;
import com.sample.post.repository.entity.like.LikeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {
}
