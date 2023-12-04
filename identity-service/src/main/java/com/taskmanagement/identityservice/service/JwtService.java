package com.taskmanagement.identityservice.service;

import io.jsonwebtoken.Claims;

import java.util.Map;

public interface JwtService {

    Claims validateAndExtractClaims(String jwt);

    String generateToken(Map<String, Object> claims, String email);
}
