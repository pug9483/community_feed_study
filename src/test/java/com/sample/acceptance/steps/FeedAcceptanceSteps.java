package com.sample.acceptance.steps;

import com.sample.post.application.dto.CreatePostRequestDto;
import io.restassured.RestAssured;
import org.springframework.http.MediaType;

import java.util.List;

public class FeedAcceptanceSteps {
    public static Long reqCreatePost(CreatePostRequestDto dto) {
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

//    public static List<PostContent>
}
