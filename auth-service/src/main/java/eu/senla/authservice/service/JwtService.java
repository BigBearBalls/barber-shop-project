package eu.senla.authservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.senla.authservice.dto.AccessTokenExtractedData;

public interface JwtService {

    AccessTokenExtractedData getAccessTokenExtractedData(String token) throws JsonProcessingException;

    void validateAccessToken(String token);
}
