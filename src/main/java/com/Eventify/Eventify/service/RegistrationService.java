package com.Eventify.Eventify.service;

import com.Eventify.Eventify.dto.registration.RegistrationRequest;
import com.Eventify.Eventify.dto.registration.RegistrationResponse;
import java.util.List;

public interface RegistrationService {

    // USER
    RegistrationResponse registerToEvent(RegistrationRequest dto);
    List<RegistrationResponse> getRegistrationsByUser(String userId);

    // ORGANIZER / ADMIN
    List<RegistrationResponse> getRegistrationsByEvent(String eventId);

    // ADMIN
    void deleteRegistrationsByEvent(String eventId);
}
