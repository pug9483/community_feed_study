package com.sample.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CommentContentTest {
    @Test
    void givenContentLengthIsOk_whenCreateCommentContent_thenReturnTextContent() {
        // given
        String contentText = "This is a comment";

        // when
        CommentContent content = new CommentContent(contentText);

        // then
        assertEquals(contentText, content.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreateCommentContent_thenThrowException() {
        // given
        String contentText = "a".repeat(101);

        assertThrows(IllegalArgumentException.class, () -> new CommentContent(contentText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "긁"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenThrowException(String koreanContentText) {
        // given
        String contextText = koreanContentText.repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(contextText));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmptyAndNull_whenCreatedCommentContext_thenThrowException(String contentText) {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(contentText));
    }
}