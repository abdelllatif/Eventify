package com.Eventify.Eventify.Controller.Public;

import com.Eventify.Eventify.dto.user.UserRegistrationRequest;
import com.Eventify.Eventify.dto.user.UserResponse;
import com.Eventify.Eventify.model.User;
import com.Eventify.Eventify.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/users")
public class PublicUserController {

    private final UserService userService;

    public PublicUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse register(@RequestBody UserRegistrationRequest dto) {
        User user = userService.registerUser(dto);
        return toResponse(user);
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