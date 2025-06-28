package cl.duocuc.ecomarket.seguridad.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class SecurityConfig {

    @Value("${ecomarket.security.private-address}")
    private String IP_PRIVADA;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/public/**").permitAll()
                        .requestMatchers("/doc/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/api/v1/private/**").access(accesoPrivadoManager())
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                );

        return http.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> autoridades = new ArrayList<>(1);
            Long permiso = jwt.getClaim("rol_id");
            if (permiso != null) {
                autoridades.add(new SimpleGrantedAuthority(String.format("ROL_%d", permiso)));
            }
            return autoridades;
        });
        return converter;
    }

    private AuthorizationManager<RequestAuthorizationContext> accesoPrivadoManager() {
        return (authenticationSupplier, context) ->
                new AuthorizationDecision(
                        IP_PRIVADA.equals(context.getRequest().getRemoteAddr())
                );
    }

}