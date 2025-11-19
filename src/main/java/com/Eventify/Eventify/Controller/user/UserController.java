package com.Eventify.Eventify.Controller.user;

import com.Eventify.Eventify.dto.UpdateUserRequest;
import com.Eventify.Eventify.dto.UserResponse;
import com.Eventify.Eventify.model.User;
import com.Eventify.Eventify.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public UserResponse getProfile(Principal principal) {
        User user = userService.getByEmail(principal.getName());
        return toResponse(user);
    }

    @PutMapping("/profile")
    public UserResponse updateProfile(Principal principal, @RequestBody UpdateUserRequest dto) {
        User user = userService.getByEmail(principal.getName());
        User updated = userService.updateUser(user.getId(), dto);
        return toResponse(updated);
    }

    private UserResponse toResponse(User u) {
        UserResponse r = new UserResponse();
        r.setId(u.getId());
        r.setName(u.getName());
        r.setEmail(u.getEmail());
        r.setRole(u.getRole().name());
        return r;
    }
}