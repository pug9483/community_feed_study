package com.sample.acceptance;

import com.sample.acceptance.utils.AcceptanceTestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeedAcceptanceTest extends AcceptanceTestTemplate {
    /**
     * User1 --- follow --> User2
     * User1 --- follow --> User3
     */
    @BeforeEach
    void setUp() {
        super.init();
    }

    /**
     * User 2 create Post 1
     * User 1 Get Post 1 From Feed
     */
    @Test
    void givenUserHasFollower_whenFollowingUserCreatePost_thenFollowerCanGetPostFromFeed() {
        // given

        // when

        // then
    }
}
