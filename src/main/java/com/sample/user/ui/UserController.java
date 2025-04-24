package com.sample.user.ui;

import com.sample.ui.Response;
import com.sample.user.application.UserService;
import com.sample.user.application.dto.CreateUserRequestDto;
import com.sample.user.application.dto.GetUserListResponseDto;
import com.sample.user.application.dto.GetUserResponseDto;
import com.sample.user.domain.User;
import com.sample.user.repository.jpa.JpaUserListQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JpaUserListQueryRepository userListQueryRepository;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        User user = userService.createUser(createUserRequestDto);
        return Response.ok(user.getId());
    }

    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getUserProfile(@PathVariable(name = "userId") Long userId) {
        return Response.ok(userService.getUserProfile(userId));
    }

    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingList(@PathVariable(name = "userId") Long userId) {
        List<GetUserListResponseDto> result = userListQueryRepository.getFollowingUserList(userId);
        return Response.ok(result);
    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollowerList(@PathVariable(name = "userId") Long userId) {
        List<GetUserListResponseDto> result = userListQueryRepository.getFollowerUserList(userId);
        return Response.ok(result);
    }
}
