package com.Eventify.Eventify.model;

import com.Eventify.Eventify.enums.RegistrationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long eventId;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;

    private LocalDateTime registeredAt=LocalDateTime.now();
}
