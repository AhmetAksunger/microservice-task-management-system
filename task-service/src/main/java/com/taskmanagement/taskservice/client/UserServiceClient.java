package com.taskmanagement.taskservice.client;

import com.taskmanagement.taskservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service", path = "/api/v1")
public interface UserServiceClient {

    @GetMapping("/users")
    ResponseEntity<List<UserDto>> getAllUsers();

    @GetMapping("/users/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable String id);

}
