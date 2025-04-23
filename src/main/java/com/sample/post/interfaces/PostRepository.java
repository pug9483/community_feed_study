package com.sample.post.interfaces;

import com.sample.post.domain.post.Post;

import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
}
