package urlshortener.backend.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * Configuration class for generating unique keys based on configured length and characters.
 */
@Configuration
@Getter
public class KeyConfiguration {

    @Value("${app.key.length}")
    private int keyLength;

    @Value("${app.key.chars}")
    private String keyChars;

    /**
     * Generates a unique key based on the configured length and characters.
     *
     * @return a unique key generated randomly from the configured characters
     */
    public String generateUniqueKey() {
        StringBuilder keyBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < keyLength; i++) {
            int randomIndex = random.nextInt(keyChars.length());
            keyBuilder.append(keyChars.charAt(randomIndex));
        }

        return keyBuilder.toString();
    }
}
