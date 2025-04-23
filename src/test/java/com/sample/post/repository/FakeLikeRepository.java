package com.sample.post.repository;

import com.sample.post.domain.comment.Comment;
import com.sample.post.domain.post.Post;
import com.sample.post.interfaces.LikeRepository;
import com.sample.user.domain.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FakeLikeRepository implements LikeRepository {
    private final Map<Post, Set<User>> postLikes = new HashMap<>();
    private final Map<Comment, Set<User>> commentLikes = new HashMap<>();

    @Override
    public boolean checkLike(Post post, User user) {
        if(postLikes.get(post) == null) {
            return false;
        }
        return postLikes.get(post).contains(user);
    }

    @Override
    public void like(Post post, User user) {
        postLikes.computeIfAbsent(post, k -> new HashSet<>()).add(user);
    }

    @Override
    public void unlike(Post post, User user){
        if (postLikes.containsKey(post)) {
            postLikes.get(post).remove(user);
            if (postLikes.get(post).isEmpty()) {
                postLikes.remove(post);
            }
        }
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        if (commentLikes.get(comment) == null) {
            return false;
        }
        return commentLikes.get(comment).contains(user);
    }

    @Override
    public void like(Comment comment, User user) {
        commentLikes.computeIfAbsent(comment, k -> new HashSet<>()).add(user);
    }

    @Override
    public void unlike(Comment comment, User user) {
        if (commentLikes.containsKey(comment)) {
            commentLikes.get(comment).remove(user);
            if (commentLikes.get(comment).isEmpty()) {
                commentLikes.remove(comment);
            }
        }
    }
}
