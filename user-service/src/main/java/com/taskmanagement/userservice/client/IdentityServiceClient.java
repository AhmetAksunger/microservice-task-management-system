package com.taskmanagement.userservice.client;

import com.taskmanagement.userservice.dto.UserValidateTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "identity-service",path = "/api/v1/auth")
public interface IdentityServiceClient {
    @PostMapping("/extract/claims/{token}")
    ResponseEntity<UserValidateTokenResponse> validateToken(@PathVariable String token);
}
