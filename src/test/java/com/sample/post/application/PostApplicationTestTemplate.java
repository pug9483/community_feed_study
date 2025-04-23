package com.sample.post.application;

import com.sample.fake.FakeObjectFactory;
import com.sample.post.application.dto.CreateCommentRequestDto;
import com.sample.post.application.dto.CreatePostRequestDto;
import com.sample.post.domain.post.Post;
import com.sample.user.application.UserService;
import com.sample.user.application.dto.CreateUserRequestDto;
import com.sample.user.domain.User;

import static com.sample.post.domain.content.PostPublicationState.PUBLIC;

/**
 * 더미 데이터 생성
 */
public class PostApplicationTestTemplate {
    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.getPostService();
    final CommentService commentService = FakeObjectFactory.getCommentService();

    final User user = userService.createUser(new CreateUserRequestDto("user1", null));
    final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));

    final CreatePostRequestDto createPostRequestDto = new CreatePostRequestDto(user.getId(), "content", PUBLIC);
    final Post post = postService.createPost(createPostRequestDto);

    final String commentContentText = "this is comment content";
    CreateCommentRequestDto createCommentRequestDto = new CreateCommentRequestDto(post.getId(), user.getId(), "this is comment content");
}
