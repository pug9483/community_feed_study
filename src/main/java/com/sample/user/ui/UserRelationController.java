package com.sample.user.ui;

import com.sample.ui.Response;
import com.sample.user.application.UserRelationService;
import com.sample.user.application.dto.FollowUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relation")
@RequiredArgsConstructor
public class UserRelationController {
    private final UserRelationService userRelationService;

    @PostMapping("/follow")
    public Response<Void> followUser(@RequestBody FollowUserRequestDto followUserRequestDto) {
        userRelationService.follow(followUserRequestDto);
        return Response.ok(null);
    }

    @PostMapping("/unfollow")
    public Response<Void> unfollowUser(@RequestBody FollowUserRequestDto followUserRequestDto) {
        userRelationService.unfollow(followUserRequestDto);
        return Response.ok(null);
    }
}
