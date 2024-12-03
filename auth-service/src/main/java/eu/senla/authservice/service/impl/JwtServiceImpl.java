package eu.senla.authservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import eu.senla.authservice.component.JwtUtils;
import eu.senla.authservice.model.Permission;
import eu.senla.authservice.service.JwtService;
import eu.senla.authservice.dto.AccessTokenExtractedData;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {

    private final JwtUtils jwtUtils;

    private final ObjectMapper objectMapper;

    @Override
    public void validateAccessToken(String token) {
        jwtUtils.validateAccessToken(token);
    }

    @Override
    public AccessTokenExtractedData getAccessTokenExtractedData(String token) throws JsonProcessingException {
        Claims claims = jwtUtils.getAccessClaims(token);
        String email = claims.getSubject();
        UUID id = UUID.fromString(claims.get("id", String.class));
        String array = objectMapper.writeValueAsString(claims.get("permissions"));
        JavaType javaType = TypeFactory.defaultInstance().constructCollectionType(Set.class, Permission.class);
        Set<Permission> permissions = objectMapper.readValue(array, javaType);
        return new AccessTokenExtractedData(email, id, permissions);
    }
}
