package com.sample.post.application.interfaces;

import com.sample.post.domain.comment.Comment;

import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Comment findById(Long id);
}
