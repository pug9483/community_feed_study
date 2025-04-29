package com.sample.post.ui;

import com.sample.post.application.CommentService;
import com.sample.post.application.dto.CreateCommentRequestDto;
import com.sample.post.application.dto.LikeRequestDto;
import com.sample.post.application.dto.UpdateCommentRequestDto;
import com.sample.post.domain.comment.Comment;
import com.sample.ui.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto createCommentRequestDto) {
        Comment comment = commentService.createComment(createCommentRequestDto);
        return Response.ok(comment.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Long> updateComment(@PathVariable(name = "commentId") Long commentId, @RequestBody UpdateCommentRequestDto updateCommentRequestDto) {
        Comment comment = commentService.updateComment(commentId, updateCommentRequestDto);
        return Response.ok(comment.getId());
    }

    @PostMapping("/like")
    public Response<Void> likeComment(@RequestBody LikeRequestDto likeRequestDto) {
        commentService.likeComment(likeRequestDto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikeComment(@RequestBody LikeRequestDto likeRequestDto) {
        commentService.unlikeComment(likeRequestDto);
        return Response.ok(null);
    }
}
