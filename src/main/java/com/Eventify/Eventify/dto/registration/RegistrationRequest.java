package com.Eventify.Eventify.dto.registration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistrationRequest {

    @NotBlank(message = "User ID must not be blank")
    private Long userId;

    @NotBlank(message = "Event ID must not be blank")
    private Long eventId;

    @NotNull(message = "Status must not be null")
    private String status;
}
