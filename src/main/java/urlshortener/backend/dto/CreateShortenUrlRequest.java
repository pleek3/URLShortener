package urlshortener.backend.dto;

import java.time.LocalDateTime;

/**
 * Represents a request to create a shortened URL.
 */
public record CreateShortenUrlRequest(String originalUrl, LocalDateTime expirationDate) {
}
