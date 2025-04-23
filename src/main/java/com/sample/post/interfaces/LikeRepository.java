package com.sample.post.interfaces;

import com.sample.post.domain.comment.Comment;
import com.sample.post.domain.post.Post;
import com.sample.user.domain.User;

public interface LikeRepository {
    boolean checkLike(Post post, User user);
    void like(Post post, User user);
    void unlike(Post post, User user);

    boolean checkLike(Comment post, User user);
    void like(Comment post, User user);
    void unlike(Comment post, User user);
}
