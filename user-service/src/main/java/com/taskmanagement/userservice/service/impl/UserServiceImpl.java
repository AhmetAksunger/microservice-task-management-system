package com.taskmanagement.userservice.service.impl;

import com.taskmanagement.userservice.dto.UserDto;
import com.taskmanagement.userservice.dto.UserSaveRequest;
import com.taskmanagement.userservice.exception.EmailAlreadyExistsException;
import com.taskmanagement.userservice.exception.UserNotFoundException;
import com.taskmanagement.userservice.model.User;
import com.taskmanagement.userservice.repository.UserRepository;
import com.taskmanagement.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::convert)
                .toList();
    }

    @Override
    public UserDto getUserById(String id) {
        return UserDto.convert(
                userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("User could not found by id: " + id))
        );
    }

    @Override
    public void saveUser(UserSaveRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists!");
        }

        User user = new User(request.getUsername(), request.getFirstName(), request.getLastName(), request.getEmail());
        userRepository.save(user);
    }
}
