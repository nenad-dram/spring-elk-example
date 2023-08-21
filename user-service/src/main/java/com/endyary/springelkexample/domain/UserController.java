package com.endyary.springelkexample.domain;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        log.info("GET /api/users request");
        List<User> userList = userService.getAll();
        List<UserResponse> userResponseList =
                userList.stream().map(userMapper::toResponse).toList();

        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> addNew(final @RequestBody UserRequest userRequest) {
        log.info("POST /api/users request");
        User user = userMapper.toEntity(userRequest);
        User savedUser = userService.save(user);
        UserResponse userResponse = userMapper.toResponse(savedUser);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable final Long id) {
        log.info("GET /api/users/{} request", id);
        User user = userService.getById(id);
        UserResponse userResponse = userMapper.toResponse(user);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable final Long id,
            @RequestBody final UserRequest userRequest) {
        log.info("PUT /api/users/{} request", id);

        User updatedUser = userMapper.toEntity(userRequest);
        updatedUser.setId(id);
        User savedUser = userService.save(updatedUser);
        UserResponse userResponse = userMapper.toResponse(savedUser);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Long id) {
        log.info("DELETE /api/users/{} request", id);

        userService.deleteById(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<UserResponse> handleNoSuchElementException(NoSuchElementException exc) {
        log.error(exc.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
