package com.card.game.card.game;

// FeignConfig.java
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            // Substitua "SEU_TOKEN_AQUI" pelo seu token de API do deckofcardsapi.com
            template.header("Authorization", "Bearer SEU_TOKEN_AQUI");
        };
    }
}

