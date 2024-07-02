package urlshortener.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import urlshortener.backend.dto.CreateShortenUrlRequest;
import urlshortener.backend.dto.ShortenUrlResponse;
import urlshortener.backend.entities.ShortenUrlEntity;
import urlshortener.backend.services.CreateShortenUrlService;

@RestController
@RequestMapping("/api/v1/urls")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CreateController {

    private final CreateShortenUrlService createShortenUrlService;

    @PostMapping("/shorten")
    public ResponseEntity<ShortenUrlResponse> createShortenUrl(@NonNull @RequestBody CreateShortenUrlRequest request) {
        ShortenUrlEntity shortenUrlEntity;

        try {
            shortenUrlEntity = createShortenUrlService.createShortenUrl(request.originalUrl(), request.expirationDate());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }


        ShortenUrlResponse response = new ShortenUrlResponse(shortenUrlEntity.getId(), shortenUrlEntity.getExpirationDate());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
