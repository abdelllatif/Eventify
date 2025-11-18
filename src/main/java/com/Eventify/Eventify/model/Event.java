package com.Eventify.Eventify.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "events")
public class Event {

    @Id
    private String id;

    private String title;

    private String description;

    private String location;

    private LocalDateTime dateTime;

    private int capacity;

    private String organizerId; // userId de l’organisateur
}
