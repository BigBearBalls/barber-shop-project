package eu.senla.authservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import eu.senla.authservice.component.JwtUtils;
import eu.senla.authservice.dto.AccessTokenExtractedData;
import eu.senla.authservice.service.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/internal/jwt/")
@RequiredArgsConstructor
public class InternalJwtController {

    private final JwtService jwtService;

    @GetMapping("access")
    public AccessTokenExtractedData getAccessTokenExtractedData(@RequestParam String accessToken)
            throws JsonProcessingException {
        jwtService.validateAccessToken(accessToken);
        return jwtService.getAccessTokenExtractedData(accessToken);
    }
}
