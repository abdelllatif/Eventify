package com.Eventify.Eventify.dto.registration;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistrationResponse {

    private Long id;

    private Long userId;
    private Long eventId;

    private String status;

    private LocalDateTime registeredAt;
}
