package eu.senla.authservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.authservice.component.JwtUtils;
import eu.senla.authservice.dto.UserCredentialsByAccessToken;
import eu.senla.authservice.model.User;
import eu.senla.authservice.service.JwtService;
import eu.senla.authservice.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {

    private final JwtUtils jwtUtils;

    private final UserService userService;

    private final ObjectMapper objectMapper;

    @Override
    public void validateAccessToken(String token) {
        jwtUtils.validateAccessToken(token);
    }

    @Override
    public UserCredentialsByAccessToken getUserCredentialsByAccessToken(String token) throws JsonProcessingException {
        Claims claims = jwtUtils.getAccessClaims(token);
//        String email = claims.getSubject();
//        UUID id = UUID.fromString(claims.get("id", String.class));
        UUID id = UUID.fromString(claims.getSubject());
        User user = userService.findById(id);
//        String array = objectMapper.writeValueAsString(claims.get("permissions"));
//        JavaType javaType = TypeFactory.defaultInstance().constructCollectionType(Set.class, Permission.class);
//        Set<Permission> permissions = objectMapper.readValue(array, javaType);
        return new UserCredentialsByAccessToken(user.getEmail(), id, user.getPermissions());
    }
}
