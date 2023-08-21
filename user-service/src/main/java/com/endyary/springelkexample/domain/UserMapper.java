package com.endyary.springelkexample.domain;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserMapper {

    public UserResponse toResponse(final User user) {
        log.info("Map response {}", user);

        UserResponse response = UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();

        log.info("Return response {}", response);

        return response;
    }

    public User toEntity(final UserRequest request) {
        log.info("Map entity {}", request);

        User user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).build();

        log.info("Return entity {}", user);
        return user;
    }
}
