package com.Eventify.Eventify.Controller.organizer;

import com.Eventify.Eventify.dto.user.UserResponse;
import com.Eventify.Eventify.dto.event.EventRequest;
import com.Eventify.Eventify.dto.event.EventResponse;
import com.Eventify.Eventify.dto.registration.RegistrationRequest;
import com.Eventify.Eventify.dto.registration.RegistrationResponse;
import com.Eventify.Eventify.service.EventService;
import com.Eventify.Eventify.service.RegistrationService;
import com.Eventify.Eventify.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/organizer")
public class OrganizerController {

    private final EventService eventService;
    private final RegistrationService registrationService;
    private final UserService userService;

    public OrganizerController(EventService eventService, RegistrationService registrationService, UserService userService) {
        this.eventService = eventService;
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @PostMapping("/events")
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest dto, Authentication authentication) {
        String organizerId = authentication.getName();
        EventResponse response = eventService.createEvent(dto, organizerId);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<EventResponse> updateEvent(
            @PathVariable String id,
            @RequestBody EventRequest dto,
            Authentication authentication
    ) {
        String organizerId = authentication.getName();
        EventResponse response = eventService.updateEvent(id, dto, organizerId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id, Authentication authentication) {
        String organizerId = authentication.getName();
        eventService.deleteEventByOrganizer(id, organizerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/events/{eventId}/participants")
    public ResponseEntity<List<RegistrationResponse>> getEventParticipants(
            @PathVariable String eventId,
            Authentication authentication
    ) {
        String organizerId = authentication.getName();
        List<RegistrationResponse> participants = registrationService.getEventParticipants(eventId, organizerId);
        return ResponseEntity.ok(participants);
    }

}