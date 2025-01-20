package eu.senla.authservice.controller;

import eu.senla.authservice.service.TokenService;
import eu.senla.common.auth.dto.RegistrationTokenResponse;
import eu.senla.httpconfiguration.security.holder.UserHolder;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tokens/")
@RequiredArgsConstructor
@Validated
public class TokenController {

    private final TokenService tokenService;

    @PostMapping
    public RegistrationTokenResponse createToken() {
        UUID userId = UserHolder.getUser().getId();
        return tokenService.createRegistrationToken(userId);
    }
}
