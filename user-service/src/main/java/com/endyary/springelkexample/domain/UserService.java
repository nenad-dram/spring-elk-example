package com.endyary.springelkexample.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User save(final User user) {
        log.info("Save new user");
        return userRepository.save(user);
    }

    public User getById(final Long id) {
        log.info("Get user with id {}", id);
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> getAll() {
        log.info("Get all users");
        return userRepository.getAll();
    }

    public User deleteById(final Long id) {
        log.info("Delete user with id {}", id);
        return userRepository.deleteById(id).orElseThrow();
    }
}
