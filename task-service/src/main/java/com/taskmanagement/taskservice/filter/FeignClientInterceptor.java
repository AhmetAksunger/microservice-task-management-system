package com.taskmanagement.taskservice.filter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FeignClientInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.nonNull(authentication)
                && Objects.nonNull(authentication.getDetails())
                && authentication.getDetails() instanceof String jwtToken) {
            requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer ".concat(jwtToken));
        }
    }
}
