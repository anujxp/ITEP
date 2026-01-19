package com.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {
    private final JwtUtil jwt;

    public JwtAuthenticationFilter(JwtUtil jwt) {
        this.jwt = jwt;
    }

    @Override
    public int getOrder() {
        return -1; // Highest priority: check security first
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String requestedRoute = request.getURI().getPath();
        
        // 1. Whitelist: Allow login/register without a token
        if (requestedRoute.startsWith("/auth")) {
            return chain.filter(exchange);
        }
        
        // 2. Security Check: Block if Authorization header is missing
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            return unauthorized(exchange, "Authorization header is missing");
        }
        
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || !authHeader.startsWith("Bearer "))
           return unauthorized(exchange,"Invalid Authorization Header");
        
        String token = authHeader.substring(7);
        try {
            // 4. Validation Check: Call your JwtUtil
            jwt.validateToken(token);
        } catch (Exception e) {
            return unauthorized(exchange, "Invalid Jwt Token");
        }
        
        // 5. Success: Forward request to Product or Order service
        return chain.filter(exchange);
    }

    public Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}