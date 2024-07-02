package urlshortener.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import urlshortener.backend.configurations.KeyConfiguration;
import urlshortener.backend.entities.ShortenUrlEntity;
import urlshortener.backend.repositories.ShortenUrlEntityRepository;

import java.time.LocalDateTime;

/**
 * Service class for creating shortened URLs.
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CreateShortenUrlService {

    private final KeyConfiguration keyConfiguration;
    private final ShortenUrlEntityRepository repository;

    /**
     * Creates a shortened URL entity with the provided original URL and expiration date.
     *
     * @param originalUrl    the original URL to shorten
     * @param expirationDate the expiration date of the shortened URL
     * @return the created ShortenUrlEntity
     * @throws RuntimeException if the expiration date is invalid
     */
    public ShortenUrlEntity createShortenUrl(@NonNull String originalUrl, @Nullable LocalDateTime expirationDate) {
        LocalDateTime now = LocalDateTime.now();

        if (expirationDate == null) {
            throw new RuntimeException("Please give a valid expiration date");
        }

        if (expirationDate.isBefore(now)) {
            throw new RuntimeException("The expiration date cannot be in the past");
        }

        if (expirationDate.isAfter(now.plusYears(1))) {
            throw new RuntimeException("The expiration date cannot be more than 1 year in the future");
        }

        return this.repository.save(new ShortenUrlEntity(keyConfiguration.generateUniqueKey(), originalUrl, expirationDate));
    }

}
