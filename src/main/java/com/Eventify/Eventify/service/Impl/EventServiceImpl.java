package com.Eventify.Eventify.service.Impl;

import com.Eventify.Eventify.enums.RegistrationStatus;
import com.Eventify.Eventify.exception.EventNotFoundException;
import com.Eventify.Eventify.exception.UnauthorizedActionException;
import com.Eventify.Eventify.model.Event;
import com.Eventify.Eventify.model.Registration;
import com.Eventify.Eventify.model.User;
import com.Eventify.Eventify.repository.EventRepository;
import com.Eventify.Eventify.repository.RegistrationRepository;
import com.Eventify.Eventify.repository.UserRepository;
import com.Eventify.Eventify.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository,
                            RegistrationRepository registrationRepository,
                            UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
    }

    //  Public
    @Override
    public List<Event> getAllPublicEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(String eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id " + eventId));
    }

    //  User
    @Override
    public Registration registerUserToEvent(String userId, String eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id " + eventId));

        // Check if user already registered
        boolean alreadyRegistered = registrationRepository.existsByUserIdAndEventId(userId, eventId);
        if (alreadyRegistered) {
            throw new UnauthorizedActionException("User already registered for this event");
        }

        // Check event capacity
        long registeredCount = registrationRepository.countByEventId(eventId);
        if (registeredCount >= event.getCapacity()) {
            throw new UnauthorizedActionException("Event is full");
        }

        Registration registration = new Registration();
        registration.setUserId(userId);
        registration.setEventId(eventId);
        registration.setRegisteredAt(LocalDateTime.now());
        registration.setStatus(RegistrationStatus.PENDING);

        return registrationRepository.save(registration);
    }

    @Override
    public List<Registration> getUserRegistrations(String userId) {
        return registrationRepository.findByUserId(userId);
    }

    //  Organizer
    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(String eventId, Event updatedEvent, String organizerId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        if (!event.getOrganizerId().equals(organizerId)) {
            throw new UnauthorizedActionException("You are not the organizer of this event");
        }

        event.setTitle(updatedEvent.getTitle());
        event.setDescription(updatedEvent.getDescription());
        event.setLocation(updatedEvent.getLocation());
        event.setDateTime(updatedEvent.getDateTime());
        event.setCapacity(updatedEvent.getCapacity());

        return eventRepository.save(event);
    }

    @Override
    public void deleteEventByOrganizer(String eventId, String organizerId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        if (!event.getOrganizerId().equals(organizerId)) {
            throw new UnauthorizedActionException("You are not the organizer of this event");
        }

        eventRepository.delete(event);
    }

    //  Admin
    @Override
    public void deleteEvent(String eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        eventRepository.delete(event);
    }

    @Override
    public List<Registration> getEventRegistrations(String eventId) {
        eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));
        return registrationRepository.findByEventId(eventId);
    }
}
