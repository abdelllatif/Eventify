package com.Eventify.Eventify.service;

import com.Eventify.Eventify.dto.registration.RegistrationRequest;
import com.Eventify.Eventify.dto.registration.RegistrationResponse;

import java.util.List;

public interface RegistrationService {
    RegistrationResponse registerToEvent(RegistrationRequest dto);

    List<RegistrationResponse> getRegistrationsByUser(String userId);

    RegistrationResponse createRegistration(RegistrationRequest request, String userId);
    List<RegistrationResponse> getRegistrationsByEvent(String eventId);
    void cancelRegistration(String registrationId, String userId);
    void deleteRegistrationsByEvent(String eventId);

    List<RegistrationResponse> getEventParticipants(String eventId, String organizerId);
}