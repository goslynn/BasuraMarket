package cl.duocuc.ecomarket.seguridad;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class EcomarketJWT implements ProveedorJWT{

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    @Value("${ecomarket.security.jwt.expiration.ms}")
    private long expirationMs;

    public EcomarketJWT(JwtEncoder encoder, JwtDecoder decoder) {
        this.encoder = encoder;
        this.decoder = decoder;
    }

    @Override
    public String generarToken(Number idUsuario, int idRol) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                                          .issuedAt(now)
                                          .expiresAt(now.plusMillis(expirationMs))
                                          .subject(idUsuario.toString())
                                          .claim("rol_id", idRol)
                                          .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public boolean validarToken(String token) {
        try {
            decoder.decode(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Integer getIdUsuario(String token) {
        Jwt decodedJwt = decoder.decode(token);
        return Integer.parseInt(decodedJwt.getSubject());
    }

    @Override
    public <T> T getClaim(String token, String claimName) {
        return decoder.decode(token).getClaim(claimName);
    }

}
