package com.jjking.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.WebFilter;

/**
 * @author 20179
 */
@Component
public class config {
    @Bean
    public WebFilter corsFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (!CorsUtils.isCorsRequest(request)) {
                return chain.filter(exchange);
            }
            ServerHttpResponse response = exchange.getResponse();
            HttpHeaders headers = response.getHeaders();
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST,GET,OPTIONS,DELETE,PUT");
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "content-type");
            headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3628800");
            return chain.filter(exchange);
        };
    }
}