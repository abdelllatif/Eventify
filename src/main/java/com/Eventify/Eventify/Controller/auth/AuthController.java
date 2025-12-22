package com.Eventify.Eventify.Controller.auth;

import com.Eventify.Eventify.dto.user.UserRegistrationRequest;
import com.Eventify.Eventify.dto.user.UserResponse;
import com.Eventify.Eventify.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login successful");
    }
}
