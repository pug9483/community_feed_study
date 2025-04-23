package com.sample.post.repository;

import com.sample.post.domain.post.Post;
import com.sample.post.application.interfaces.PostRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakePostRepository implements PostRepository {
    private final Map<Long, Post> store = new HashMap<>();

    @Override
    public Post save(Post post) {
        if(post.getId() != null) {
            store.put(post.getId(), post);
            return post;
        }

        Long id = store.size() + 1L;
        Post newPost = new Post(id, post.getAuthor(), post.getContentObject());
        store.put(id, newPost);

        return newPost;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
