package com.sample.post.repository;

import com.sample.post.domain.comment.Comment;
import com.sample.post.application.interfaces.CommentRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeCommentRepository implements CommentRepository {
    private final Map<Long, Comment> store = new HashMap<>();

    @Override
    public Comment save(Comment comment) {
        if(comment.getId() != null) {
            store.put(comment.getId(), comment);
            return comment;
        }

        Long id = store.size() + 1L;
        Comment newComment = new Comment(id, comment.getPost(), comment.getAuthor(), comment.getContentObject());
        store.put(id, newComment);

        return newComment;
    }

    @Override
    public Comment findById(Long id) {
        return store.get(id);
    }
}
