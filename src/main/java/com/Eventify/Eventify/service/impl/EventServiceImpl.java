package com.Eventify.Eventify.service.impl;

import com.Eventify.Eventify.dto.event.EventRequest;
import com.Eventify.Eventify.dto.event.EventResponse;
import com.Eventify.Eventify.exception.EventNotFoundException;
import com.Eventify.Eventify.mapper.EventMapper;
import com.Eventify.Eventify.model.Event;
import com.Eventify.Eventify.repository.EventRepository;
import com.Eventify.Eventify.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventResponse createEvent(EventRequest dto, String organizerId) {
        Event event = eventMapper.toEntity(dto);
        event.setOrganizerId(organizerId);
        return eventMapper.toDto(eventRepository.save(event));
    }

    @Override
    public EventResponse updateEvent(String eventId, EventRequest dto, String organizerId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id " + eventId));

        if (!event.getOrganizerId().equals(organizerId)) {
            throw new RuntimeException("Unauthorized to update this event"); // can create UnauthorizedActionException
        }

        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setDateTime(dto.getDateTime());
        event.setCapacity(dto.getCapacity());

        return eventMapper.toDto(eventRepository.save(event));
    }

    @Override
    public void deleteEventByOrganizer(String eventId, String organizerId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id " + eventId));

        if (!event.getOrganizerId().equals(organizerId)) {
            throw new RuntimeException("Unauthorized to delete this event");
        }
        eventRepository.deleteById(eventId);
    }

    @Override
    public void deleteEventByAdmin(String eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new EventNotFoundException("Event not found with id " + eventId);
        }
        eventRepository.deleteById(eventId);
    }
}
