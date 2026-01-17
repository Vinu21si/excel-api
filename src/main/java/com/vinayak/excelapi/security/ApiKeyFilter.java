package com.vinayak.excelapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// 🔴 API KEY SECURITY DISABLED (JWT IS USED INSTEAD)
// @Component
// @Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiKeyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // Do nothing — pass request forward
        filterChain.doFilter(request, response);
    }
}
