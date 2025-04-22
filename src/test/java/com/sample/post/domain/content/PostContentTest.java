package com.sample.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {
	@Test
	void givenContentLengthIsOk_whenCreated_thenReturnTextContext() {
		// given
		String text = "this is a test";

		// when
		PostContent content = new PostContent(text);

		// then
		assertEquals(text, content.contentText);
	}

	@Test
	void givenContentLengthIsOver_whenCreated_thenThrowError() {
		// given
		String contentText = "a".repeat(501);

		// when, then
		assertThrows(IllegalArgumentException.class, () -> new PostContent(contentText));
	}

	/**
	 * 테스트에 주입할 값을 애노테이션에 배열로 지정한다.
	 * 테스트를 실행하면 배열을 순회하면서, 테스트 메소드에 인수로 배열에 지정된 값들을 주입해서 테스트한다.
	 * 하나의 테스트에는 하나의 인수(argument)만 전달할 수 있다.
	 * 자료형: byte, short, int, long, float, double, char, boolean, String
	 */
	@ParameterizedTest
	@ValueSource(strings = {"뷁", "닭", "굵", "삵", "슳"})
	void givenContentLengthIsOverAndKorean_whenCreated_thenThrowException(String koreanContentText) {
		// given
		String contentText = koreanContentText.repeat(501);

		// when, then
		assertThrows(IllegalArgumentException.class, () -> new PostContent(contentText));
	}

	@Test
	void givenContentLengthIsUnder_whenCreated_thenThrowException() {
		// given
		String contentText = "a".repeat(4);

		// when, then
		assertThrows(IllegalArgumentException.class, () -> new PostContent(contentText));
	}

	@ParameterizedTest
	@NullAndEmptySource // Null과 비어있는 값 추가
	void givenContentLengthIsEmptyAndNull_whenCreatedCommentContext_thenThrowException(String commentContext) {
		// when, then
		assertThrows(IllegalArgumentException.class, () -> new PostContent(commentContext));
	}
}
