package com.vinayak.excelapi.controller.auth;

import com.vinayak.excelapi.model.Role;
import com.vinayak.excelapi.security.jwt.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        if ("admin".equals(username) && "admin123".equals(password)) {
            return ResponseEntity.ok(
                    Map.of("token", jwtUtil.generateToken(username, Role.ADMIN.name()))
            );
        }

        if ("manager".equals(username) && "manager123".equals(password)) {
            return ResponseEntity.ok(
                    Map.of("token", jwtUtil.generateToken(username, Role.MANAGER.name()))
            );
        }

        if ("user".equals(username) && "user123".equals(password)) {
            return ResponseEntity.ok(
                    Map.of("token", jwtUtil.generateToken(username, Role.USER.name()))
            );
        }

        return ResponseEntity.status(401)
                .body(Map.of("error", "Invalid credentials"));
    }
}
