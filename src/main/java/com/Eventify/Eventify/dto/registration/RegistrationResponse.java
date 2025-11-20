package com.Eventify.Eventify.dto.registration;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistrationResponse {

    private String id;

    private String userId;
    private String eventId;

    private String status;

    private LocalDateTime registeredAt;
}
