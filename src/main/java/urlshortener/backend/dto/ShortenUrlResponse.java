package urlshortener.backend.dto;

import java.time.LocalDateTime;

/**
 * Represents a response containing information about a shortened URL.
 */
public record ShortenUrlResponse(String key, LocalDateTime expirationDate) {
}
