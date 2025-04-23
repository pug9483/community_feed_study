package com.sample.post.repository.entity.like;

import com.sample.common.repository.entity.TimeBaseEntity;
import com.sample.post.domain.comment.Comment;
import com.sample.post.domain.post.Post;
import com.sample.user.domain.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.sample.post.repository.entity.like.LikeTarget.COMMENT;
import static com.sample.post.repository.entity.like.LikeTarget.POST;

@Entity
@Table(name="community_like")
@NoArgsConstructor
@Getter
public class LikeEntity extends TimeBaseEntity {

    @EmbeddedId
    private LikeIdEntity id;

    public LikeEntity(Post post, User likedUser) {
        this.id = new LikeIdEntity(post.getId(), likedUser.getId(), POST.name());
    }

    public LikeEntity(Comment comment, User likedUser) {
        this.id = new LikeIdEntity(comment.getId(), likedUser.getId(), COMMENT.name());
    }
}
