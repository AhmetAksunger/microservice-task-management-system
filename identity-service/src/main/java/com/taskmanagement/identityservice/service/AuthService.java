package com.taskmanagement.identityservice.service;

import com.taskmanagement.identityservice.dto.UserSaveRequest;
import com.taskmanagement.identityservice.dto.UserTokenRequest;
import com.taskmanagement.identityservice.dto.UserTokenResponse;
import com.taskmanagement.identityservice.dto.UserValidateTokenResponse;

public interface AuthService {

    UserTokenResponse register(UserSaveRequest userSaveRequest);

    UserTokenResponse generateToken(UserTokenRequest request);

    UserValidateTokenResponse validateAndExtractClaims(String jwt);
}
