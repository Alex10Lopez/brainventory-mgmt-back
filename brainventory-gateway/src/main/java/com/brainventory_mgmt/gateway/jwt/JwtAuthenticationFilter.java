package com.brainventory_mgmt.gateway.jwt;

import com.brainventory_mgmt.gateway.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();

        // Excluir endpoints de autenticación
        if (path.startsWith("/auth/")) {
            return chain.filter(exchange);
        }

        String token = getTokenFromRequest(exchange);

        if (token == null || !jwtService.isTokenValid(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String permission = jwtService.extractPermission(token);

        // Verificar permisos para las rutas
        if (!hasPermission(path, permission)) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    private boolean hasPermission(String path, String permission) {
        // GLOBAL_ADMIN tiene acceso a todo
        if ("GLOBAL_ADMIN".equals(permission)) {
            return true;
        }

        // Ruta profile
        if (path.startsWith("/profile/")) {
            return "HR_ADMIN".equals(permission) || "ASSETS_ADMIN".equals(permission) || "INFRASTRUCTURE_ADMIN".equals(permission);
        }

        // Rutas de HR
        List<String> hrPaths = Arrays.asList("/api/employee", "/api/job-role");
        if (hrPaths.stream().anyMatch(path::startsWith)) {
            return "HR_ADMIN".equals(permission);
        }

        // Rutas de Infrastructure (excepto room-device)
        List<String> infraPaths = Arrays.asList("/api/building", "/api/room", "/api/department");
        if (infraPaths.stream().anyMatch(path::startsWith)) {
            return "INFRASTRUCTURE_ADMIN".equals(permission);
        }

        // Rutas de Assets
        List<String> assetsPaths = Arrays.asList("/api/it-device", "/api/io-device", "/api/hardware");
        if (assetsPaths.stream().anyMatch(path::startsWith)) {
            return "ASSETS_ADMIN".equals(permission);
        }

        // Ruta room-device (compartida)
        if (path.startsWith("/api/room-device")) {
            return "ASSETS_ADMIN".equals(permission) || "INFRASTRUCTURE_ADMIN".equals(permission);
        }

        return false;
    }

    private String getTokenFromRequest(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}