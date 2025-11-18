package com.Eventify.Eventify.repository;

import com.Eventify.Eventify.model.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends MongoRepository<Registration, String> {

    List<Registration> findByUserId(String userId);

    List<Registration> findByEventId(String eventId);

    Optional<Registration> findByUserIdAndEventId(String userId, String eventId);
}

