package hu.nye.progtech.torpedo.config;

import hu.nye.progtech.torpedo.service.util.MapToString;
import hu.nye.progtech.torpedo.service.util.MapUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Battleship game utilities.
 */
@Configuration
public class UtilConfiguration {
    @Bean
    MapUtil mapUtil() {
        return new MapUtil();
    }

    @Bean
    MapToString mapToString() {
        return new MapToString();
    }
}
