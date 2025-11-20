package com.Eventify.Eventify.mapper;

import com.Eventify.Eventify.dto.registration.RegistrationRequest;
import com.Eventify.Eventify.dto.registration.RegistrationResponse;
import com.Eventify.Eventify.model.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    RegistrationMapper INSTANCE = Mappers.getMapper(RegistrationMapper.class);

    Registration toEntity(RegistrationRequest dto);

    RegistrationResponse toDto(Registration registration); // ⚡ from toResponse -> toDto
}
