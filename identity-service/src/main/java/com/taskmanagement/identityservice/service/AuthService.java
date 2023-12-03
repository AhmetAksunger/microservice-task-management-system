package com.taskmanagement.identityservice.service;

import com.taskmanagement.identityservice.dto.UserSaveRequest;
import com.taskmanagement.identityservice.dto.UserTokenRequest;
import com.taskmanagement.identityservice.dto.UserTokenResponse;

public interface AuthService {

    UserTokenResponse register(UserSaveRequest userSaveRequest);

    UserTokenResponse generateToken(UserTokenRequest request);

    void validateToken(String jwt);
}
