package urlshortener.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;
import urlshortener.backend.services.RedirectService;

/**
 * Controller for handling redirection requests.
 * Maps incoming requests with a specific key to the original URL.
 */
@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("/api/v1/redirect")
public class RedirectController {

    private final RedirectService redirectService;

    /**
     * Redirects to the original URL associated with the given key.
     *
     * @param key the unique key associated with the original URL
     * @return a RedirectView to the original URL
     * @throws ResponseStatusException if no original URL is found for the provided key
     */
    @GetMapping("/{key}")
    public RedirectView redirectToOriginalUrl(@PathVariable String key) {
        RedirectView redirectView = redirectService.createRedirectViewToOriginalUrl(key);

        if (redirectView == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No original URL found for key: " + key);
        }

        return redirectView;
    }
}
