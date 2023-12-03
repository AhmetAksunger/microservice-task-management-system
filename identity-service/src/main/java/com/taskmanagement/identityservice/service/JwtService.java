package com.taskmanagement.identityservice.service;

import java.util.Map;

public interface JwtService {

    void validateToken(String jwt);

    String generateToken(Map<String, Object> claims, String email);
}
