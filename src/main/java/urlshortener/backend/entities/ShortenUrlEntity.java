package urlshortener.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents a shortened URL entity stored in the database.
 */
@Getter
@Setter
@Entity
@Table(name = "shorten_url_entity")
@NoArgsConstructor
@AllArgsConstructor
public class ShortenUrlEntity {

    /**
     * Constructs a ShortenUrlEntity with the specified key, original URL, and expiration date.
     *
     * @param key            the unique key associated with the shortened URL
     * @param originalUrl    the original URL that was shortened
     * @param expirationDate the expiration date of the shortened URL
     */
    public ShortenUrlEntity(String key, String originalUrl, LocalDateTime expirationDate) {
        this.id = key;
        this.originalUrl = originalUrl;
        this.creationDate = LocalDateTime.now();
        this.expirationDate = expirationDate;
    }

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "original_url", nullable = false)
    private String originalUrl;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

}
