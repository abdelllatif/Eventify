package com.Eventify.Eventify.Controller.user;

import com.Eventify.Eventify.dto.registration.RegistrationRequest;
import com.Eventify.Eventify.dto.registration.RegistrationResponse;
import com.Eventify.Eventify.dto.user.UpdateUserRequest;
import com.Eventify.Eventify.dto.user.UserResponse;
import com.Eventify.Eventify.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getProfile(Principal principal) {
        UserResponse user = userService.getByEmail(principal.getName());
        return ResponseEntity.ok(user);
    }


    @PutMapping("/profile")
    public ResponseEntity<UserResponse> updateProfile(
            Principal principal,
            @Valid @RequestBody UpdateUserRequest dto
    ) {
        UserResponse current = userService.getByEmail(principal.getName());
        UserResponse updated = userService.updateUser(current.getId(), dto);
        return ResponseEntity.ok(updated);
    }



}
