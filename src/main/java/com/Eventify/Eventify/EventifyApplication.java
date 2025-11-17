package com.Eventify.Eventify;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventifyApplication {

    public static void main(String[] args) {
        // Load .env variables
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        // Build the MongoDB URI manually
        String mongoUri = String.format(
                "mongodb://%s:%s@localhost:27017/%s?authSource=admin",
                dotenv.get("MONGO_USER"),
                dotenv.get("MONGO_PASSWORD"),
                dotenv.get("MONGO_DB")
        );

        // Set Spring properties
        System.setProperty("spring.data.mongodb.uri", mongoUri);
        System.setProperty("spring.security.user.name", dotenv.get("SPRING_SECURITY_USER"));
        System.setProperty("spring.security.user.password", dotenv.get("SPRING_SECURITY_PASSWORD"));

        // Start Spring Boot
        SpringApplication.run(EventifyApplication.class, args);
    }
}
