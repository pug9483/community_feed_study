package com.sample.post.application;

import com.sample.post.application.dto.CreateCommentRequestDto;
import com.sample.post.application.dto.LikeRequestDto;
import com.sample.post.application.dto.UpdateCommentRequestDto;
import com.sample.post.domain.comment.Comment;
import com.sample.post.domain.post.Post;
import com.sample.post.application.interfaces.CommentRepository;
import com.sample.post.application.interfaces.LikeRepository;
import com.sample.user.application.UserService;
import com.sample.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public Comment getComment(Long id) {
        return commentRepository.findById(id);
    }

    public Comment createComment(CreateCommentRequestDto createCommentRequestDto) {
        Post post = postService.getPost(createCommentRequestDto.postId());
        User user = userService.getUser(createCommentRequestDto.userId());

        Comment comment = Comment.createComment(post, user, createCommentRequestDto.content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, UpdateCommentRequestDto updateCommentRequestDto) {
        Comment comment = getComment(commentId);
        User user = userService.getUser(updateCommentRequestDto.userId());
        comment.updateComment(user, updateCommentRequestDto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto likeRequestDto) {
        Comment comment = getComment(likeRequestDto.targetId());
        User user = userService.getUser(likeRequestDto.userId());

        if(likeRepository.checkLike(comment, user)){
            return;
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unlikeComment(LikeRequestDto likeRequestDto) {
        Comment comment = getComment(likeRequestDto.targetId());
        User user = userService.getUser(likeRequestDto.userId());

        if(likeRepository.checkLike(comment, user)){
            comment.unlike();
            likeRepository.unlike(comment, user);
        }
    }
}
