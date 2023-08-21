package com.endyary.springelkexample.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserRepository {

    private final Map<Long, User> userMap = new WeakHashMap<>();

    @PostConstruct
    void postConstruct() {
        log.info("Initializing the user list");

        User user1 = User.builder()
                .id(1L)
                .email("john.doe@mail.com")
                .firstName("John")
                .lastName("Doe")
                .build();
        User user2 = User.builder()
                .id(2L)
                .email("jane.doe@mail.com")
                .firstName("Jane")
                .lastName("Doe")
                .build();
        User user3 = User.builder()
                .id(3L)
                .email("bart.mile@mail.com")
                .firstName("Bart")
                .lastName("Mile")
                .build();
        User user4 = User.builder()
                .id(4L)
                .email("ann.smith@mail.com")
                .firstName("Ann")
                .lastName("Smith")
                .build();

        userMap.put(1L, user1);
        userMap.put(2L, user2);
        userMap.put(3L, user3);
        userMap.put(4L, user4);
    }

    public User save(final User user) {
        log.info("Saving a new user");

        if (user.getId() == null) {
            user.setId(getNextId());
        }
        return userMap.put(user.getId(), user);
    }

    public Optional<User> findById(final Long id) {
        log.info("Finding user with id {}", id);
        return Optional.ofNullable(userMap.get(id));
    }

    public List<User> getAll() {
        log.info("Returning all users");
        return userMap.values().stream().toList();
    }

    public Optional<User> deleteById(final Long id) {
        log.info("Deleting user with id {}", id);
        return Optional.ofNullable(userMap.remove(id));
    }

    private Long getNextId() {
        log.info("Get next id");
        return (long) (userMap.size() + 1);
    }
}
