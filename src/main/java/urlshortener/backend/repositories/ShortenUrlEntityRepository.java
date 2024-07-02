package urlshortener.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import urlshortener.backend.entities.ShortenUrlEntity;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Repository interface for accessing ShortenUrlEntity objects in the database.
 */
@Repository
public interface ShortenUrlEntityRepository extends JpaRepository<ShortenUrlEntity, String> {

    /**
     * Deletes ShortenUrlEntity entries from the database with expiration dates before the specified date.
     *
     * @param expirationDate the expiration date to compare against
     * @return the number of entities deleted
     */
    long deleteByExpirationDateBeforeAllIgnoreCase(@NonNull LocalDateTime expirationDate);

    /**
     * Retrieves a ShortenUrlEntity by its ID (case-insensitive).
     *
     * @param id the ID of the entity to retrieve
     * @return an Optional containing the ShortenUrlEntity, or empty if not found
     */
    Optional<ShortenUrlEntity> findByIdIgnoreCase(String id);

    /**
     * Retrieves a ShortenUrlEntity by its original URL (case-insensitive).
     *
     * @param originalUrl the original URL of the entity to retrieve
     * @return an Optional containing the ShortenUrlEntity, or empty if not found
     */
    Optional<ShortenUrlEntity> findByOriginalUrlIgnoreCase(String originalUrl);
}
