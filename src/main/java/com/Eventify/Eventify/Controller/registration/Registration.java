package com.Eventify.Eventify.Controller.registration;

import com.Eventify.Eventify.dto.registration.RegistrationRequest;
import com.Eventify.Eventify.dto.registration.RegistrationResponse;
import com.Eventify.Eventify.dto.user.UserResponse;
import com.Eventify.Eventify.service.RegistrationService;
import com.Eventify.Eventify.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/registrations")
public class Registration {
    private final UserService userService;
    private final RegistrationService registrationService;

    public Registration(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @PostMapping("/events/{eventId}/register")
    public ResponseEntity<RegistrationResponse> registerToEvent(
            Principal principal,
            @PathVariable("eventId") Long eventId
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

    @DeleteMapping("/registrations/{registrationId}")
    public ResponseEntity<Void> cancelRegistration(
            Principal principal,
            @PathVariable("registrationId") Long registrationId
    ) {
        UserResponse current = userService.getByEmail(principal.getName());
        registrationService.cancelRegistration(registrationId, current.getId());
        return ResponseEntity.noContent().build();
    }
}
