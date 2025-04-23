package com.sample.post.application;

import com.sample.fake.FakeObjectFactory;
import com.sample.post.application.dto.CreatePostRequestDto;
import com.sample.post.application.dto.LikeRequestDto;
import com.sample.post.application.dto.UpdatePostRequestDto;
import com.sample.post.domain.post.Post;
import com.sample.user.application.UserService;
import com.sample.user.application.dto.CreateUserRequestDto;
import com.sample.user.domain.User;
import org.junit.jupiter.api.Test;

import static com.sample.post.domain.content.PostPublicationState.PUBLIC;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PostServiceTest extends PostApplicationTestTemplate{
    private final UserService userService = FakeObjectFactory.getUserService();
    private final PostService postService = FakeObjectFactory.getPostService();

    private final User user = userService.createUser(new CreateUserRequestDto("user1", null));
    private final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));

    private final CreatePostRequestDto createPostRequestDto = new CreatePostRequestDto(user.getId(), "content", PUBLIC);

    @Test
    void givenCreatePostRequestDto_whenCreate_thenReturnPost() {
        // when
        Post savedPost = postService.createPost(createPostRequestDto);

        // then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(post.getId(), savedPost.getId());
        assertEquals(user.getId(), savedPost.getAuthor().getId());
    }

    @Test
    void givenCreateUserRequestDto_whenUpdatePost_thenReturnPost() {
        // given
        Post savedPost = postService.createPost(createPostRequestDto);

        // when
        Post updatedPost = postService.updatePost(savedPost.getId(), new UpdatePostRequestDto(savedPost.getId(), user.getId(), "updated content", PUBLIC));

        // then
        assertEquals(updatedPost.getId(), savedPost.getId());
        assertEquals("updated content", updatedPost.getContent());
        assertEquals(updatedPost.getAuthor(), user);
    }

    @Test
    void givenCreatedPost_whenLiked_thenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(createPostRequestDto);

        // when
        postService.likePost(new LikeRequestDto(savedPost.getId(), otherUser.getId()));

        // then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenCreatedPost_whenLikedTwice_thenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(createPostRequestDto);

        // when
        postService.likePost(new LikeRequestDto(savedPost.getId(), otherUser.getId()));
        postService.likePost(new LikeRequestDto(savedPost.getId(), otherUser.getId()));

        // then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenCreatedPostLiked_whenUnliked_thenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(createPostRequestDto);
        postService.likePost(new LikeRequestDto(savedPost.getId(), otherUser.getId()));

        // when
        postService.unlikePost(new LikeRequestDto(savedPost.getId(), otherUser.getId()));

        // then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenCreatedPost_whenUnliked_thenReturnPostWithoutLike() {
        // given
        Post savedPost = postService.createPost(createPostRequestDto);

        // when
        postService.unlikePost(new LikeRequestDto(savedPost.getId(), otherUser.getId()));

        // then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(0, post.getLikeCount());
    }
}
