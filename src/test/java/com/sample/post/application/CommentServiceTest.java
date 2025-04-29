package com.sample.post.application;

import com.sample.post.application.dto.LikeRequestDto;
import com.sample.post.application.dto.UpdateCommentRequestDto;
import com.sample.post.domain.comment.Comment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentServiceTest extends PostApplicationTestTemplate{
    @Test
    void givenCreateCommentRequestDto_whenCreate_thenReturnComment() {
        // when
        Comment savedComment = commentService.createComment(createCommentRequestDto);

        // then
        String content = savedComment.getContent();
        assertEquals(commentContentText, content);
    }

    @Test
    void givenCreateCommentRequestDto_whenUpdateComment_thenReturnUpdatedComment() {
        // given
        Comment savedComment = commentService.createComment(createCommentRequestDto);

        // when
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(user.getId(), "updated-Content");
        commentService.updateComment(savedComment.getId(), updateCommentRequestDto);

        // then
        Comment updatedComment = commentService.getComment(savedComment.getId());
        assertEquals(savedComment.getId(), updatedComment.getId());
        assertEquals(savedComment.getAuthor(), updatedComment.getAuthor());
        assertEquals(savedComment.getContent(), updatedComment.getContent());
    }

    @Test
    void givenCreateCommentRequestDto_whenLikeComment_thenReturnCommentWithLike() {
        // given
        Comment savedComment = commentService.createComment(createCommentRequestDto);

        // when
        commentService.likeComment(new LikeRequestDto(savedComment.getId(), otherUser.getId()));

        // then
        Comment comment = commentService.getComment(savedComment.getId());
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCreateCommentRequestDtoAndLike_whenUnlikeComment_thenReturnCommentWithLike() {
        // given
        Comment savedComment = commentService.createComment(createCommentRequestDto);
        commentService.likeComment(new LikeRequestDto(savedComment.getId(), otherUser.getId()));

        // when
        commentService.unlikeComment(new LikeRequestDto(savedComment.getId(), otherUser.getId()));

        // then
        Comment comment = commentService.getComment(savedComment.getId());
        assertEquals(0, comment.getLikeCount());
    }
}
