package com.taskmanagement.gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    private static final List<String> openApiEndpoints = List.of(
            "/api/v1/auth/token",
            "/api/v1/auth/register"
    );

    public Predicate<ServerHttpRequest> isSecured =
            serverHttpRequest ->
                    openApiEndpoints
                            .stream()
                            .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
