package br.edu.ufape.ecommerce.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ResourceServerConfig {

    @Autowired
    private KeycloakJwtAuthenticationConverter keycloakJwtAuthenticationConverter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http/*
            .authorizeRequests().requestMatchers("/security/**")
            .authenticated().anyRequest().permitAll()
            .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(keycloakJwtAuthenticationConverter);
 */
                .authorizeRequests((authorizeRequests) ->
                                authorizeRequests
                                        .anyRequest().authenticated()
                        )
                        .oauth2ResourceServer((oauth2ResourceServer) ->
                                oauth2ResourceServer
                                        .jwt((jwt) ->
                                                jwt.jwtAuthenticationConverter(keycloakJwtAuthenticationConverter)
                                        )
                        );
        return http.build();
    }

    @Bean
    public jwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.key).build();
    }
}
