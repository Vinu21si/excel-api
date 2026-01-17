package com.vinayak.excelapi.security;

import org.springframework.lang.NonNull;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${api.key}")
    private String apiKey;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Public endpoint
        if (path.equals("/test")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Protect everything else
        String clientKey = request.getHeader("X-API-KEY");

        if (clientKey == null || !clientKey.equals(apiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("""
                {
                  "error": "Unauthorized",
                  "message": "Missing or invalid API key"
                }
                """);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
