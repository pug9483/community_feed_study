package com.sample.acceptance.steps;

import com.sample.post.application.dto.CreatePostRequestDto;
import com.sample.post.ui.dto.GetPostContentResponseDto;
import io.restassured.RestAssured;
import org.springframework.http.MediaType;

import java.util.List;

public class FeedAcceptanceSteps {
    public static Long requestCreatePost(CreatePostRequestDto dto) {
        return RestAssured
                .given().log().all()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/user")
                .then().log().all()
                .extract()
                .jsonPath()
                .getObject("value", Long.class);
    }

    public static List<GetPostContentResponseDto> requestFeed(Long userId) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/feed{userId}", userId)
                .then().log().all()
                .extract()
                .jsonPath()
                .getList("value", GetPostContentResponseDto.class);
    }
}
