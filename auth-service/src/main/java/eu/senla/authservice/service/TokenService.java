package eu.senla.authservice.service;

import eu.senla.common.auth.dto.RegistrationTokenResponse;
import java.util.UUID;

public interface TokenService {

    RegistrationTokenResponse createRegistrationToken(UUID id);

    UUID parseRegistrationTokenToTeamLeaderId(String token);

    void deleteByToken(String token);
}
