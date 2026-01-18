package com.vinayak.excelapi.controller.auth;

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

    @PostMapping(
            value = "/login",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<?> login(
            @RequestBody(required = false) Map<String, String> request
    ) {

        // 🔒 Absolute safety: no crashes
        if (request == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Request body missing"));
        }

        String username = request.get("username");
        String password = request.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Username or password missing"));
        }

        // 🔴 Phase 2: hardcoded auth (intentional)
        if ("admin".equals(username) && "admin123".equals(password)) {

            String token = jwtUtil.generateToken(username, "ADMIN");

            return ResponseEntity.ok(
                    Map.of("token", token)
            );
        }

        return ResponseEntity.status(401)
                .body(Map.of("error", "Invalid credentials"));
    }
}
