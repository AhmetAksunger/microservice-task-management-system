package com.taskmanagement.gateway.filter;

import com.taskmanagement.gateway.enums.Header;
import com.taskmanagement.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator routeValidator;
    private final JwtUtil jwtUtil;

    @Value("${secret.gateway.key}")
    private String gatewaySecretKey;

    public AuthenticationFilter(RouteValidator routeValidator, JwtUtil jwtUtil) {
        super(Config.class);
        this.routeValidator = routeValidator;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            ServerHttpRequest request = null;
            if (routeValidator.isSecured.test(exchange.getRequest())) {

                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return onError(exchange);
                }

                try {
                    List<String> authHeaders = Optional.ofNullable(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION))
                            .orElseThrow();

                    String bearerToken = Optional.ofNullable(authHeaders.get(0))
                            .filter(auth -> auth.startsWith("Bearer "))
                            .orElseThrow();

                    String jwtToken = bearerToken.substring(7);

                    jwtUtil.validateToken(jwtToken);

                    request = exchange.getRequest().mutate()
                            .header(Header.GATEWAY_TOKEN.value(), gatewaySecretKey)
                            .header(Header.USER_EMAIL.value(), jwtUtil.extractEmail(jwtToken))
                            .build();
                } catch (Exception e) {
                    return onError(exchange);
                }
            }

            if (Objects.isNull(request)) {
                return chain.filter(exchange);
            }

            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    public static class Config {
    }
}
