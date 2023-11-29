package com.taskmanagement.userservice.config;

import com.taskmanagement.userservice.model.User;
import com.taskmanagement.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;

    public InitialDataConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("ahmt21", "Ahmet", "Aksünger", "ahmetaksunger@ahmet.com", "12345");
        userRepository.save(user);
    }
}
