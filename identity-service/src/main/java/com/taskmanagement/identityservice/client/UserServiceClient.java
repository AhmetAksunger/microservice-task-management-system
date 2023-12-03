package com.taskmanagement.identityservice.client;

import com.taskmanagement.identityservice.dto.UserSaveRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", path = "/api/v1")
public interface UserServiceClient {

    @PostMapping("/users")
    ResponseEntity<Void> saveUser(@RequestBody UserSaveRequest userSaveRequest);

}
