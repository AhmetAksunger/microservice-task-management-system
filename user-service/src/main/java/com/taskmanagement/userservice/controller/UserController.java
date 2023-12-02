package com.taskmanagement.userservice.controller;

import com.taskmanagement.userservice.dto.UserDto;
import com.taskmanagement.userservice.service.UserService;
import org.hibernate.validator.constraints.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Value("${user.service.count}")
    private Integer count;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("Get All Users Endpoint");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable @UUID String id) {
        logger.info("Get User By Id Endpoint");
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/users/count")
    public ResponseEntity<Integer> getCount() {
        return ResponseEntity.ok(count);
    }
}
