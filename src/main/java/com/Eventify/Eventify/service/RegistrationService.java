package com.Eventify.Eventify.service;

import com.Eventify.Eventify.dto.registration.RegistrationRequest;
import com.Eventify.Eventify.dto.registration.RegistrationResponse;

import java.util.List;

public interface RegistrationService {

    RegistrationResponse registerToEvent(RegistrationRequest dto);

    RegistrationResponse createRegistration(RegistrationRequest request, Long userId);

    List<RegistrationResponse> getRegistrationsByUser(Long userId);

    List<RegistrationResponse> getRegistrationsByEvent(Long eventId);

    void cancelRegistration(Long registrationId, Long userId);

    void deleteRegistrationsByEvent(Long eventId);

    List<RegistrationResponse> getEventParticipants(Long eventId, Long organizerId);
}
