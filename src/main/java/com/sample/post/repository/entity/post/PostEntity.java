package com.sample.post.repository.entity.post;

import com.sample.common.domain.PositiveIntegerCounter;
import com.sample.common.repository.entity.TimeBaseEntity;
import com.sample.post.domain.content.PostContent;
import com.sample.post.domain.content.PostPublicationState;
import com.sample.post.domain.post.Post;
import com.sample.user.repository.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import static jakarta.persistence.ConstraintMode.NO_CONSTRAINT;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "community_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostEntity extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="author_id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    private UserEntity author;

    @Convert(converter = PostPublicationStateConverter.class)
    private PostPublicationState state;

    private String content;

    private Integer likeCount;

    @ColumnDefault("0")
    private int commentCount;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getContent();
        this.state = post.getState();
        this.likeCount = post.getLikeCount();
    }

    public Post toPost() {
        return Post.builder()
                .id(id)
                .author(author.toUser())
                .content(new PostContent(content))
                .state(state)
                .positiveIntegerCounter(new PositiveIntegerCounter(likeCount))
                .build();
    }
}
