package com.sample.post.application.dto;

import com.sample.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(Long userId, String content, PostPublicationState state) {
}
