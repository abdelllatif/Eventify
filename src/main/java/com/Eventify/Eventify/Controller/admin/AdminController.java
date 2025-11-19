package com.Eventify.Eventify.Controller.admin;


import com.Eventify.Eventify.dto.user.UpdateRoleRequest;
import com.Eventify.Eventify.dto.user.UpdateUserRequest;
import com.Eventify.Eventify.dto.user.UserResponse;
import com.Eventify.Eventify.enums.Role;
import com.Eventify.Eventify.model.User;
import com.Eventify.Eventify.service.EventService;
import com.Eventify.Eventify.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final EventService eventService;

    public AdminController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @PutMapping("/users/{id}/role")
    public UserResponse updateRole(@PathVariable String id, @RequestBody UpdateRoleRequest dto) {
        Role roleEnum = Role.valueOf(dto.getRole().toUpperCase());
        User updated = userService.updateRole(id, roleEnum);
        return toResponse(updated);
    }

    @PutMapping("/users/{id}")
    public UserResponse updateUser(@PathVariable String id, @RequestBody UpdateUserRequest dto) {
        User updated = userService.updateUser(id, dto);
        return toResponse(updated);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted by admin");
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