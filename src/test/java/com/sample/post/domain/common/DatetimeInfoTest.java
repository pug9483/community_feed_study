package com.sample.post.domain.common;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


class DatetimeInfoTest {
	@Test
	void givenCreated_whenUpdated_thenTimeAndStateUpdated() {
		// given
		DatetimeInfo datetimeInfo = new DatetimeInfo();
		LocalDateTime localDateTime = datetimeInfo.getDateTime();

		// when
		datetimeInfo.updateEditDatetime();

		// then
		assertTrue(datetimeInfo.isEdited());
		assertNotEquals(localDateTime, datetimeInfo.getDateTime());
	}
}
