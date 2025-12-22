package com.Eventify.Eventify.dto.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventResponse {

    private Long id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime dateTime;
    private int capacity;

    private String organizerId;
}
