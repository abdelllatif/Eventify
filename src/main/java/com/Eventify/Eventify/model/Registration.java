package com.Eventify.Eventify.model;

import com.Eventify.Eventify.enums.RegistrationStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("registrations")
public class Registration {

    @Id
    private String id;

    private String userId;
    private String eventId;

    private RegistrationStatus status;

    private LocalDateTime registeredAt;
}
