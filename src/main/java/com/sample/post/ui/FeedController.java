package com.sample.post.ui;

import com.sample.post.repository.post_queue.UserPostQueueQueryRepository;
import com.sample.post.ui.dto.GetPostContentResponseDto;
import com.sample.ui.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
    private final UserPostQueueQueryRepository queryRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeed(@PathVariable(name = "userId") Long userId, Long lastContentId) {
        List<GetPostContentResponseDto> result = queryRepository.getContentResponse(userId, lastContentId);
        return Response.ok(result);
    }
}
