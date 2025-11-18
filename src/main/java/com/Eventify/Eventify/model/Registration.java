package com.Eventify.Eventify.model;

import com.Eventify.Eventify.enums.RegistrationStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "registrations")
public class Registration {

    @Id
    private String id;

    private String userId;

    private String eventId;

    private LocalDateTime registeredAt;

    private RegistrationStatus status; // ex: "PENDING", "CONFIRMED", "CANCELLED"
}
