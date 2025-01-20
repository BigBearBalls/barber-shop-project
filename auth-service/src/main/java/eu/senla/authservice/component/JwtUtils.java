package eu.senla.authservice.component;

import eu.senla.authservice.model.User;
import eu.senla.common.enums.ErrorCode;
import eu.senla.common.exception.JwtValidateException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    @Value("${spring.application.security.jwt.access-key.value}")
    private String jwtAccessSecret;

    @Value("${spring.application.security.jwt.refresh-key.value}")
    private String jwtRefreshSecret;

    @Value("${spring.application.security.jwt.registration-key.value}")
    private String jwtRegistrationSecret;

    @Value("${spring.application.security.jwt.access-key.expiration-time}")
    private Integer jwtAccessExpiration;

    @Value("${spring.application.security.jwt.refresh-key.expiration-time}")
    private Integer jwtRefreshExpiration;

    /**
     * Token validation
     * If token invalid - exception will be thrown
     *
     * @param token token
     */
    private void validateToken(String token, Key key) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parse(token);
        } catch (Exception e) {
            throw new JwtValidateException(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()),
                    ErrorCode.ERR_JWT_VALIDATION_EXCEPTION);
        }
    }

    /**
     * Generate access token
     *
     * @param user user object
     * @return token
     */
    public String generateAccessToken(User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(jwtAccessExpiration)
                .atZone(ZoneId.systemDefault()).toInstant();
        return Jwts.builder()
//                .setSubject(user.getEmail())
                .setSubject(user.getId().toString())
                .setExpiration(Date.from(accessExpirationInstant))
                .signWith(getAccessSigningKey())
//                .claim("id", user.getId())
                .compact();
    }

    /**
     * Generate refresh token
     *
     * @param user user object
     * @return token
     */

    public String generateRefreshToken(User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant = now.plusMinutes(jwtRefreshExpiration)
                .atZone(ZoneId.systemDefault()).toInstant();
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(Date.from(refreshExpirationInstant))
                .signWith(getRefreshSigningKey())
                .compact();
    }

    /**
     * Validate access token
     * If token invalid - exception will be thrown
     *
     * @param token token
     */
    public void validateAccessToken(String token) {
        validateToken(token, getAccessSigningKey());
    }

    /**
     * Validate refresh token
     * If token invalid - exception will be thrown
     *
     * @param token token
     */
    public void validateRefreshToken(String token) {
        validateToken(token, getRefreshSigningKey());
    }

    private Claims getClaims(String token, Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Extract claim from access token
     *
     * @param token token
     * @return extracted claims
     */
    public Claims getAccessClaims(String token) {
        return getClaims(token, getAccessSigningKey());
    }

    /**
     * Extract claim from refresh token
     *
     * @param token token
     * @return extracted claims
     */
    public Claims getRefreshClaims(String token) {
        return getClaims(token, getRefreshSigningKey());
    }

    /**
     * Get refresh signing key
     *
     * @return key
     */
    private Key getRefreshSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtRefreshSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Get access signing key
     *
     * @return key
     */
    private Key getAccessSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtAccessSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Key getRegistrationKeyWithSalt(String salt) {

        byte[] keyBytes = Decoders.BASE64.decode(jwtRegistrationSecret);
        byte[] saltBytes = salt.getBytes();
        byte[] keys = new byte[keyBytes.length + saltBytes.length];
        for (int i = 0; i < keyBytes.length; i++) {
            keys[i] = keyBytes[i];
        }
        for (int i = 0; i < saltBytes.length; i++) {
            keys[keyBytes.length + i] = saltBytes[i];
        }
        return Keys.hmacShaKeyFor(keys);
    }

    public String generateSalt() {
        return UUID.randomUUID().toString();
    }

    public void validateRegistrationToken(String token, Key key) {
        try {
            validateToken(token, key);
        } catch (Exception e) {
            throw new JwtValidateException(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()),
                    ErrorCode.ERR_JWT_VALIDATION_EXCEPTION);
        }
    }

    public Claims getRegistrationClaims(String token, Key key) {
        return getClaims(token, key);
    }
}