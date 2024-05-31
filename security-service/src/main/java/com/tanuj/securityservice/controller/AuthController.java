package com.tanuj.securityservice.controller;



import com.tanuj.securityservice.model.User;
import com.tanuj.securityservice.service.UserService;
import com.tanuj.securityservice.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest authRequest) {
        User user = userService.registerUser(authRequest.getUsername(), authRequest.getPassword(), authRequest.getRole());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) {
        User user = userService.validateUser(authRequest.getUsername(), authRequest.getPassword());
        if (user != null) {
            String token = jwtTokenProvider.generateToken(user.getUsername(), user.getRole());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody TokenRequest tokenRequest) {
        if (jwtTokenProvider.validateToken(tokenRequest.getToken())) {
            String role = jwtTokenProvider.getRoleFromToken(tokenRequest.getToken());
            String access = role.equals("ROLE_INSTRUCTOR") ? "Access to Quiz and Question services" : "Access to Quiz service only";
            return ResponseEntity.ok(access);
        }
        return ResponseEntity.status(401).body("Invalid token");
    }

    static class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    static class AuthRequest {
        private String username;
        private String password;
        private String role;

        // Getters and setters

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    static class TokenRequest {
        private String token;

        // Getters and setters

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}

