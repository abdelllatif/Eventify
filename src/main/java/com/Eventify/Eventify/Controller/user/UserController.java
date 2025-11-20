package com.Eventify.Eventify.Controller.user;

import com.Eventify.Eventify.dto.registration.RegistrationRequest;
import com.Eventify.Eventify.dto.registration.RegistrationResponse;
import com.Eventify.Eventify.dto.user.UpdateUserRequest;
import com.Eventify.Eventify.dto.user.UserResponse;
import com.Eventify.Eventify.service.RegistrationService;
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
    private final RegistrationService registrationService;

    public UserController(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
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


    @PostMapping("/events/{eventId}/register")
    public ResponseEntity<RegistrationResponse> registerToEvent(
            Principal principal,
            @PathVariable("eventId") String eventId
    ) {
        UserResponse current = userService.getByEmail(principal.getName());

        RegistrationRequest req = new RegistrationRequest();
        req.setUserId(current.getId());
        req.setEventId(eventId);
        req.setStatus("PENDING");

        RegistrationResponse resp = registrationService.registerToEvent(req);
        return ResponseEntity.status(201).body(resp);
    }


    @GetMapping("/registrations")
    public ResponseEntity<List<RegistrationResponse>> getMyRegistrations(Principal principal) {
        UserResponse current = userService.getByEmail(principal.getName());
        List<RegistrationResponse> registrations = registrationService.getRegistrationsByUser(current.getId());
        return ResponseEntity.ok(registrations);
    }
}
