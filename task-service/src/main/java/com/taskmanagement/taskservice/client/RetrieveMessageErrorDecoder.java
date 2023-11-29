package com.taskmanagement.taskservice.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanagement.taskservice.exception.ErrorResponse;
import com.taskmanagement.taskservice.exception.UserNotFoundException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class RetrieveMessageErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        ErrorResponse errorResponse = null;

        try {
            String responseBody = Util.toString(response.body().asReader());
            ObjectMapper mapper = new ObjectMapper();
            errorResponse = mapper.readValue(responseBody, ErrorResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        switch (response.status()) {
            case 404:
                throw new UserNotFoundException(errorResponse);
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
