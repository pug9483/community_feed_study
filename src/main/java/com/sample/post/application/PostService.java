package com.sample.post.application;

import com.sample.post.application.dto.CreatePostRequestDto;
import com.sample.post.application.dto.LikeRequestDto;
import com.sample.post.application.dto.UpdatePostRequestDto;
import com.sample.post.domain.post.Post;
import com.sample.post.application.interfaces.LikeRepository;
import com.sample.post.application.interfaces.PostRepository;
import com.sample.user.application.UserService;
import com.sample.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public Post getPost(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(CreatePostRequestDto createPostRequestDto) {
        User author = userService.getUser(createPostRequestDto.userId());
        Post post = new Post(null, author, createPostRequestDto.content());
        return postRepository.save(post);
    }

    public Post updatePost(Long id, UpdatePostRequestDto updatePostRequestDto) {
        Post post = getPost(id);
        User user = userService.getUser(updatePostRequestDto.userId());

        post.updatePost(user, updatePostRequestDto.content(), updatePostRequestDto.state());

        return postRepository.save(post);
    }

    public void likePost(LikeRequestDto likeRequestDto) {
        Post post = getPost(likeRequestDto.targetId());
        User user = userService.getUser(likeRequestDto.userId());

        if(likeRepository.checkLike(post, user)) {
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }

    public void unlikePost(LikeRequestDto likeRequestDto) {
        Post post = getPost(likeRequestDto.targetId());
        User user = userService.getUser(likeRequestDto.userId());

        if(likeRepository.checkLike(post, user)) {
            post.unlike();
            likeRepository.unlike(post, user);
        }
    }
}
