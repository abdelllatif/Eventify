package com.Eventify.Eventify.repository;

import com.Eventify.Eventify.model.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends MongoRepository<Registration, String> {

    // USER — récupérer inscriptions d’un user
    List<Registration> findByUserId(String userId);

    // ORGANIZER / ADMIN — récupérer inscriptions d’un event
    List<Registration> findByEventId(String eventId);

    // USER — vérifier si déjà inscrit
    Optional<Registration> findByUserIdAndEventId(String userId, String eventId);

    boolean existsByUserIdAndEventId(String userId, String eventId);



    // ADMIN — supprimer toutes les inscriptions liées à un événement
    void deleteByEventId(String eventId);
    long countByEventId(String eventId);

}
