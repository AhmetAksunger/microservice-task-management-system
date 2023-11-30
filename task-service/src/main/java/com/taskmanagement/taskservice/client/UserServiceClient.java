package com.taskmanagement.taskservice.client;

import com.taskmanagement.taskservice.dto.UserDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service", path = "/api/v1")
public interface UserServiceClient {

    Logger logger = LoggerFactory.getLogger(UserServiceClient.class);

    @GetMapping("/users")
    @CircuitBreaker(name = "getAllUsersCircuitBreaker", fallbackMethod = "getAllUsersFallback")
    ResponseEntity<List<UserDto>> getAllUsers();

    /**
     * Only to show an example of resilience-4j.
     * @param e Exception.
     */
    @SuppressWarnings({"unused"})
    default void getAllUsersFallback(Exception e) {
        logger.info("Get All Users endpoint has failed!");
    }

    @GetMapping("/users/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable String id);

}
