package com.Eventify.Eventify.dto.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Date and time are required")
    @Future(message = "Event date must be in the future")
    private LocalDateTime dateTime;

    @Positive(message = "Capacity must be greater than 0")
    private int capacity;
}
