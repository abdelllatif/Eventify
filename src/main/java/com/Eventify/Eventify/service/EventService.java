package com.Eventify.Eventify.service;

import com.Eventify.Eventify.dto.event.EventRequest;
import com.Eventify.Eventify.dto.event.EventResponse;
import java.util.List;

public interface EventService {

    // USER
    List<EventResponse> getAllEvents();

    // ORGANIZER
    EventResponse createEvent(EventRequest dto, String organizerId);
    EventResponse updateEvent(String eventId, EventRequest dto, String organizerId);
    void deleteEventByOrganizer(String eventId, String organizerId);

    // ADMIN
    void deleteEventByAdmin(String eventId);
}
