package com.taskmanagement.identityservice.service.impl;

import com.taskmanagement.identityservice.client.UserServiceClient;
import com.taskmanagement.identityservice.dto.UserSaveRequest;
import com.taskmanagement.identityservice.dto.UserTokenRequest;
import com.taskmanagement.identityservice.dto.UserTokenResponse;
import com.taskmanagement.identityservice.exception.UnauthorizedException;
import com.taskmanagement.identityservice.model.UserCredential;
import com.taskmanagement.identityservice.repository.UserCredentialRepository;
import com.taskmanagement.identityservice.service.AuthService;
import com.taskmanagement.identityservice.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserServiceClient userServiceClient;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserCredentialRepository userCredentialRepository, PasswordEncoder passwordEncoder, JwtService jwtService, UserServiceClient userServiceClient, AuthenticationManager authenticationManager) {
        this.userCredentialRepository = userCredentialRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userServiceClient = userServiceClient;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserTokenResponse register(UserSaveRequest userSaveRequest) {

        if (userCredentialRepository.existsByEmail(userSaveRequest.getEmail())) {
            throw new UnauthorizedException();
        }

        final String encodedPassword = passwordEncoder.encode(userSaveRequest.getPassword());
        UserCredential userCredential = new UserCredential(userSaveRequest.getEmail(), encodedPassword);
        userCredentialRepository.save(userCredential);

        userServiceClient.saveUser(userSaveRequest);

        return new UserTokenResponse(jwtService.generateToken(userCredential.getClaims(), userCredential.getEmail()));
    }

    @Override
    public UserTokenResponse generateToken(UserTokenRequest request) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        if (authenticate.isAuthenticated()) {
            UserCredential userCredential = (UserCredential) authenticate.getPrincipal();
            return new UserTokenResponse(jwtService.generateToken(userCredential.getClaims(), userCredential.getEmail()));
        }

        throw new UnauthorizedException();
    }

    @Override
    public void validateToken(String jwt) {
        jwtService.validateToken(jwt);
    }
}
