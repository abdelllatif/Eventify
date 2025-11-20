package com.Eventify.Eventify.mapper;

import com.Eventify.Eventify.dto.event.EventRequest;
import com.Eventify.Eventify.dto.event.EventResponse;
import com.Eventify.Eventify.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    Event toEntity(EventRequest dto);

    EventResponse toDto(Event event); // ⚡ from toResponse -> toDto
}
