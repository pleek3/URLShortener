package urlshortener.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;
import urlshortener.backend.entities.ShortenUrlEntity;
import urlshortener.backend.repositories.ShortenUrlEntityRepository;

import java.util.Optional;

/**
 * Service class for handling redirection to original URLs.
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RedirectService {

    private final ShortenUrlEntityRepository repository;

    /**
     * Creates a RedirectView to the original URL associated with the given key.
     *
     * @param key the unique key associated with the original URL
     * @return a RedirectView to the original URL if found, or null if not found
     */
    public RedirectView createRedirectViewToOriginalUrl(@NonNull String key) {
        Optional<ShortenUrlEntity> entity = repository.findByIdIgnoreCase(key);
        return entity.map(shortenUrlEntity -> new RedirectView(shortenUrlEntity.getOriginalUrl()))
                .orElse(null);
    }

}
