package urlshortener.backend.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import urlshortener.backend.repositories.ShortenUrlEntityRepository;

import java.time.LocalDateTime;

/**
 * This task is responsible for cleaning up expired URLs from the database.
 * It runs once a day at midnight.
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ExpiredUrlCleanupTask {

    private final ShortenUrlEntityRepository repository;

    /**
     * Cleans up expired URLs by deleting entries from the database that have an expiration date before the current time.
     * This method is scheduled to run at midnight every day.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanupExpiredUrls() {
        LocalDateTime now = LocalDateTime.now();

        System.out.println("Executing expired URL cleanup task at " + now);
        repository.deleteByExpirationDateBeforeAllIgnoreCase(now);
    }
}
