package com.sample.post.repository;

import com.sample.post.application.interfaces.LikeRepository;
import com.sample.post.domain.comment.Comment;
import com.sample.post.domain.post.Post;
import com.sample.post.repository.entity.comment.CommentEntity;
import com.sample.post.repository.entity.like.LikeEntity;
import com.sample.post.repository.entity.post.PostEntity;
import com.sample.post.repository.jpa.JpaCommentRepository;
import com.sample.post.repository.jpa.JpaLikeRepository;
import com.sample.post.repository.jpa.JpaPostRepository;
import com.sample.user.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    /**
     * LIKE은 생성과 삭제밖에 없다.
     * Spring Data JPA의 경우, save()를 사용하면 내부적으로 merge()를 호출한다. (항상 조회를 하고 저장을 하는 일이 발생함)
     * 항상 insert()만 발생할 수 있도록 persistence를 사용한다.
     */
    @PersistenceContext
    private final EntityManager entityManager;

    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;
    private final JpaLikeRepository jpaLikeRepository;

    @Override
    public boolean checkLike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        entityManager.persist(likeEntity);
        jpaPostRepository.updatePostLikeCount(new PostEntity(post));
    }

    @Override
    @Transactional
    public void unlike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaPostRepository.updatePostLikeCount(new PostEntity(post));
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        entityManager.persist(likeEntity);
        jpaCommentRepository.updateLikeCount(new CommentEntity(comment));
    }

    @Override
    @Transactional
    public void unlike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaCommentRepository.updateLikeCount(new CommentEntity(comment));
    }
}
