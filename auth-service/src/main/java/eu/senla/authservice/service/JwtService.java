package eu.senla.authservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.senla.authservice.dto.UserCredentialsByAccessToken;

public interface JwtService {

    UserCredentialsByAccessToken getUserCredentialsByAccessToken(String token) throws JsonProcessingException;

    void validateAccessToken(String token);
}
