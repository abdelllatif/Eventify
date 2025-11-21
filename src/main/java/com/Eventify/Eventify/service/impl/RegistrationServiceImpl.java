package com.Eventify.Eventify.service.impl;

import com.Eventify.Eventify.dto.registration.RegistrationRequest;
import com.Eventify.Eventify.dto.registration.RegistrationResponse;
import com.Eventify.Eventify.enums.RegistrationStatus;
import com.Eventify.Eventify.exception.EventNotFoundException;
import com.Eventify.Eventify.exception.UnauthorizedActionException;
import com.Eventify.Eventify.mapper.RegistrationMapper;
import com.Eventify.Eventify.exception.EntityNotFoundException;
import com.Eventify.Eventify.model.Event;
import com.Eventify.Eventify.model.Registration;
import com.Eventify.Eventify.repository.EventRepository;
import com.Eventify.Eventify.repository.RegistrationRepository;
import com.Eventify.Eventify.service.RegistrationService;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;
    private final RegistrationMapper registrationMapper;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository,
                                   EventRepository eventRepository,
                                   RegistrationMapper registrationMapper) {
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
        this.registrationMapper = registrationMapper;
    }

    @Override
    public RegistrationResponse registerToEvent(RegistrationRequest dto) {
        Event event = eventRepository.findById(dto.getEventId())
                .orElseThrow(() -> new EventNotFoundException("Event not found with id " + dto.getEventId()));

        if (registrationRepository.existsByUserIdAndEventId(dto.getUserId(), dto.getEventId())) {
            throw new UnauthorizedActionException("User already registered for this event");
        }

        Registration registration = registrationMapper.toEntity(dto);
        registration.setRegisteredAt(LocalDateTime.now());
        registration.setStatus(RegistrationStatus.PENDING);

        return registrationMapper.toDto(registrationRepository.save(registration));
    }

    @Override
    public List<RegistrationResponse> getRegistrationsByUser(String userId) {
        return registrationRepository.findByUserId(userId).stream()
                .map(registrationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RegistrationResponse createRegistration(RegistrationRequest request, String userId) {
        return null;
    }

    @Override
    public List<RegistrationResponse> getRegistrationsByEvent(String eventId) {
        return registrationRepository.findByEventId(eventId).stream()
                .map(registrationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRegistrationsByEvent(String eventId) {
        registrationRepository.deleteByEventId(eventId);
    }

    // In RegistrationServiceImpl.java

    @Override
    public void cancelRegistration(String registrationId, String userId) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new EntityNotFoundException("Registration not found with id: " + registrationId));

        if (!registration.getUserId().equals(userId)) {
            throw new UnauthorizedActionException("User is not authorized to cancel this registration.");
        }

        registrationRepository.delete(registration);
    }


    @Override
    public List<RegistrationResponse> getEventParticipants(String eventId, String organizerId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + eventId));

        if (!event.getOrganizerId().equals(organizerId)) {
            throw new UnauthorizedActionException("Only the event organizer can view participants.");
        }

        return registrationRepository.findByEventId(eventId).stream()
                .map(registrationMapper::toDto)
                .collect(Collectors.toList());
    }

}