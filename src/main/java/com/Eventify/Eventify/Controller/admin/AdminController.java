package com.Eventify.Eventify.Controller.admin;

import com.Eventify.Eventify.dto.user.UpdateRoleRequest;
import com.Eventify.Eventify.dto.user.UserResponse;
import com.Eventify.Eventify.service.EventService;
import com.Eventify.Eventify.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @PutMapping("/users/{id}/role")
    public ResponseEntity<UserResponse> updateUserRole(
            @PathVariable("id") String id,
            @Valid @RequestBody UpdateRoleRequest dto
    ) {
        UserResponse updated = userService.updateRole(id, dto);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") String id) {
        eventService.deleteEventByAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
