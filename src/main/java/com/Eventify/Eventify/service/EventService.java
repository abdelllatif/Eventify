package com.Eventify.Eventify.service;

import com.Eventify.Eventify.model.Event;
import com.Eventify.Eventify.model.Registration;

import java.util.List;

public interface EventService {

    // ================= Public =================
    List<Event> getAllPublicEvents(); // GET /api/public/events

    Event getEventById(String eventId); // Get single event by id

    // ================= User =================
    Registration registerUserToEvent(String userId, String eventId); // POST /api/user/events/{id}/register
    List<Registration> getUserRegistrations(String userId); // GET /api/user/registrations

    // ================= Organizer =================
    Event createEvent(Event event); // POST /api/organizer/events
    Event updateEvent(String eventId, Event updatedEvent, String organizerId); // PUT /api/organizer/events/{id}
    void deleteEventByOrganizer(String eventId, String organizerId); // DELETE /api/organizer/events/{id}

    // ================= Admin =================
    void deleteEvent(String eventId); // DELETE /api/admin/events/{id}

    // Optional: list all registrations for an event (maybe for organizer/admin)
    List<Registration> getEventRegistrations(String eventId);
}
