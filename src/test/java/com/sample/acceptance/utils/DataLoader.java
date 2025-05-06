package com.sample.acceptance.utils;

import com.sample.user.application.dto.CreateUserRequestDto;
import com.sample.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

import static com.sample.acceptance.steps.UserAcceptanceSteps.createUser;
import static com.sample.acceptance.steps.UserAcceptanceSteps.followUser;

@Component
public class DataLoader {

    public void loadData() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test user", "");
        createUser(dto);
        createUser(dto);
        createUser(dto);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }
}
