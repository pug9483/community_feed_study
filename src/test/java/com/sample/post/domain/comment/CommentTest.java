package com.sample.post.domain.comment;

import com.sample.post.domain.content.CommentContent;
import com.sample.post.domain.content.PostContent;
import com.sample.post.domain.post.Post;
import com.sample.user.domain.User;
import com.sample.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {
    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);

    private final Post post = new Post(1L, user, new PostContent("content"));
    private final Comment comment = new Comment(1L, post, user, new CommentContent("content"));

    @Test
    void givenCommentCreated_whenLike_thenLikeCountShouldBe1() {
        // when
        comment.like(otherUser);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenLikeBySelf_thenThrowException() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentCreatedAndLike_whenUnLike_thenLikeCountShouldBe0() {
        // given
        comment.like(otherUser);

        // when
        comment.unlike();

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenUnLike_thenLikeCountShouldBe0() {
        // when
        comment.unlike();

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenComment_whenUpdateContent_thenShouldBeUpdated() {
        // given
        String newContentText = "new content";

        // when
        comment.updateComment(user, newContentText);

        // then
        assertEquals(newContentText, comment.getContent());
    }

    @Test
    void givenComment_whenUpdateCommentOver100_thenThrowException() {
        // given
        String newContentText = "a".repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(user, newContentText));
    }
}
