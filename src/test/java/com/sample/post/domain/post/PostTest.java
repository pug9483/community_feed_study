package com.sample.post.domain.post;

import com.sample.post.domain.content.PostContent;
import com.sample.user.domain.User;
import com.sample.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static com.sample.post.domain.content.PostPublicationState.PUBLIC;
import static org.junit.jupiter.api.Assertions.*;

class PostTest {
    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);

    private final Post post = new Post(1L, user, new PostContent("content"));

    @Test
    void givenPostCreated_whenLike_thenCountShouldBe1() {
        // when
        post.like(otherUser);

        // then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenLikeByUser_thenThrowException() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    void givenPostCreatedAndLike_whenUnlike_thenLikeCountShouldBe0(){
        // given
        post.like(otherUser);

        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUnlike_thenLikeCountShouldBe0() {
        // when
        post.unlike();

        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated() {
        // given
        String newPostContent = "new content";

        // when
        post.updatePost(user, newPostContent, PUBLIC);

        // then
        assertEquals(newPostContent, post.getContent());
    }

    @Test
    void givenPostCreated_whenUpdatedOtherUserContent_thenThrowException() {
        // given
        String newPostContent = "new content";

        // when, then
        assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, newPostContent, PUBLIC));
    }
}