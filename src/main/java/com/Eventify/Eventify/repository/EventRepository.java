package com.Eventify.Eventify.repository;

import com.Eventify.Eventify.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {

    // Organizer — liste des événements créés par un organisateur
    List<Event> findByOrganizerId(String organizerId);

    // Recherche avancée (optionnel mais utile)
    List<Event> findByTitleContainingIgnoreCase(String title);

    List<Event> findByLocationContainingIgnoreCase(String location);

    List<Event> findByCapacityLessThan(int capacity);

    List<Event> findByDateTimeBefore(LocalDateTime dateTime);

    List<Event> findByDateTimeAfter(LocalDateTime dateTime);
}
