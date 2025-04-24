package com.sample.post.ui;

import com.sample.post.application.PostService;
import com.sample.post.application.dto.CreatePostRequestDto;
import com.sample.post.application.dto.LikeRequestDto;
import com.sample.post.application.dto.UpdatePostRequestDto;
import com.sample.post.domain.post.Post;
import com.sample.ui.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public Response<Long> createPost(@RequestBody CreatePostRequestDto createPostRequestDto) {
        Post post = postService.createPost(createPostRequestDto);
        return Response.ok(post.getId());
    }

    @PatchMapping("/{postId}")
    public Response<Long> updatePost(@PathVariable(name = "postId") Long postId, @RequestBody UpdatePostRequestDto updatePostRequestDto) {
        Post post = postService.updatePost(postId, updatePostRequestDto);
        return Response.ok(post.getId());
    }

    @PostMapping("/like")
    public Response<Void> likePost(@RequestBody LikeRequestDto likeRequestDto) {
        postService.likePost(likeRequestDto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikePost(@RequestBody LikeRequestDto likeRequestDto) {
        postService.unlikePost(likeRequestDto);
        return Response.ok(null);
    }
}
