package com.sample.acceptance;

import com.sample.acceptance.steps.FeedAcceptanceSteps;
import com.sample.acceptance.utils.AcceptanceTestTemplate;
import com.sample.post.application.dto.CreatePostRequestDto;
import com.sample.post.domain.content.PostPublicationState;
import com.sample.post.ui.dto.GetPostContentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sample.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static com.sample.acceptance.steps.FeedAcceptanceSteps.requestFeed;
import static com.sample.post.domain.content.PostPublicationState.PUBLIC;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void givenUserHasFollowerAndCreatePost_whenFollowerUserRequestFeed_thenFollowerCanGetPostFromFeed() {
        // given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post", PUBLIC);
        Long createdPostId = requestCreatePost(dto);

        // when, 팔로워 피드를 요청
        List<GetPostContentResponseDto> result = requestFeed(1L);

        // then
        assertEquals(1, result.size());
        assertEquals(createdPostId, result.get(0).getId());
    }
}
