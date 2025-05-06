package com.sample.post.repository.jpa;

import com.sample.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
    @Modifying
    @Query(value = "UPDATE PostEntity p " +
            "SET p.content = :#{#postEntity.getContent()}, " +
            "p.state = :#{#postEntity.getState()}, " +
            "p.updDt = now() " +
            "WHERE p.id = :#{#postEntity.getId()}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p " +
            "SET p.likeCount = :#{#postEntity.getLikeCount()}, "  +
            "p.updDt = now() " +
            "WHERE p.id = :#{#postEntity.getId()}")
    void updatePostLikeCount(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p " +
            "SET p.commentCount = p.commentCount + 1, " +
            "p.updDt = now() " +
            "WHERE p.id = :id")
    void increaseCommentCount(Long id);

    @Query("SELECT p.id FROM PostEntity p WHERE p.author.id = :authorId")
    List<Long> findAllPostIdsByAuthorId(Long authorId);

    @Modifying
    @Query("UPDATE PostEntity p SET p.commentCounter = p.commentCounter + 1 WHERE p.id = :postId")
    void increaseCommentCounter(Long postId);
}
