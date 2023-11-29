package com.taskmanagement.userservice.service;

import com.taskmanagement.userservice.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(String id);
}
