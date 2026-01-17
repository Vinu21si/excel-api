package com.vinayak.excelapi.controller.auth;

import com.vinayak.excelapi.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    // TEMP login (hardcoded user)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        // 🔴 Hardcoded check (we replace with DB later)
        if ("admin".equals(username) && "admin123".equals(password)) {

            String token = jwtUtil.generateToken(username);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body(
                Map.of("error", "Invalid credentials")
        );
    }
}
