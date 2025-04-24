package com.sample.post.application.interfaces;

import com.sample.post.domain.post.Post;

import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Post findById(Long id);
}
