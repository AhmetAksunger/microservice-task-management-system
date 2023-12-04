package com.taskmanagement.identityservice.controller;

import com.taskmanagement.identityservice.dto.UserSaveRequest;
import com.taskmanagement.identityservice.dto.UserTokenRequest;
import com.taskmanagement.identityservice.dto.UserTokenResponse;
import com.taskmanagement.identityservice.dto.UserValidateTokenResponse;
import com.taskmanagement.identityservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserTokenResponse> register(@RequestBody UserSaveRequest userSaveRequest) {
        return ResponseEntity.ok(authService.register(userSaveRequest));
    }

    @PostMapping("/token")
    public ResponseEntity<UserTokenResponse> token(@RequestBody UserTokenRequest request) {
        return ResponseEntity.ok(authService.generateToken(request));
    }

    @PostMapping("/extract/claims/{token}")
    public ResponseEntity<UserValidateTokenResponse> validateToken(@PathVariable String token) {
        return ResponseEntity.ok(authService.validateAndExtractClaims(token));
    }
}
