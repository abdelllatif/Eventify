package com.Eventify.Eventify.repository;

import com.Eventify.Eventify.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByOrganizerId(Long organizerId);

    List<Event> findByTitleContainingIgnoreCase(String title);

    List<Event> findByLocationContainingIgnoreCase(String location);

    List<Event> findByCapacityLessThan(int capacity);

    List<Event> findByDateTimeBefore(LocalDateTime dateTime);

    List<Event> findByDateTimeAfter(LocalDateTime dateTime);
}
