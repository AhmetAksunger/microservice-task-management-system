package com.taskmanagement.userservice.service;

import com.taskmanagement.userservice.dto.UserDto;
import com.taskmanagement.userservice.dto.UserSaveRequest;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(String id);

    void saveUser(UserSaveRequest request);
}
