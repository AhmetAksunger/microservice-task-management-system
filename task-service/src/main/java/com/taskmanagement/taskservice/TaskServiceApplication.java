package com.taskmanagement.taskservice;

import com.taskmanagement.taskservice.client.RetrieveMessageErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class TaskServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskServiceApplication.class, args);
    }

    @Bean
    public ErrorDecoder getErrorDecoder() {
        return new RetrieveMessageErrorDecoder();
    }
}
