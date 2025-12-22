package com.Eventify.Eventify.service;

import com.Eventify.Eventify.dto.event.EventRequest;
import com.Eventify.Eventify.dto.event.EventResponse;

import java.util.List;

public interface EventService {

    List<EventResponse> getAllEvents();

    EventResponse createEvent(EventRequest dto, Long organizerId);
    EventResponse updateEvent(Long eventId, EventRequest dto, Long organizerId);
    void deleteEventByOrganizer(Long eventId, Long organizerId);

    void deleteEventByAdmin(Long eventId);
}
