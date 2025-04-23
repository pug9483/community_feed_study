package com.sample.fake;

import com.sample.post.application.CommentService;
import com.sample.post.application.PostService;
import com.sample.post.application.interfaces.CommentRepository;
import com.sample.post.application.interfaces.LikeRepository;
import com.sample.post.application.interfaces.PostRepository;
import com.sample.post.repository.FakeCommentRepository;
import com.sample.post.repository.FakeLikeRepository;
import com.sample.post.repository.FakePostRepository;
import com.sample.user.application.UserRelationService;
import com.sample.user.application.UserService;
import com.sample.user.interfaces.UserRelationRepository;
import com.sample.user.repository.FakeUserRelationRepository;
import com.sample.user.repository.FakeUserRepository;

public class FakeObjectFactory {
    private static final FakeUserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService, fakeUserRelationRepository);
    private static final PostService postService = new PostService(userService, fakePostRepository, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(userService, postService, fakeCommentRepository, fakeLikeRepository);

    private FakeObjectFactory() {}

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }
}
