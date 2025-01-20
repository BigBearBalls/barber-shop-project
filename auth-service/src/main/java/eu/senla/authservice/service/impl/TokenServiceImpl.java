package eu.senla.authservice.service.impl;

import eu.senla.authservice.client.DepartmentUserClient;
import eu.senla.authservice.component.JwtUtils;
import eu.senla.authservice.model.RegistrationToken;
import eu.senla.authservice.repository.RegistrationTokenRepository;
import eu.senla.authservice.service.TokenService;
import eu.senla.common.auth.dto.RegistrationTokenResponse;
import eu.senla.common.department.dto.response.ShortDepartmentUserInfoDTO;
import eu.senla.common.enums.DepartmentRole;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final DepartmentUserClient departmentUserClient;
    private final JwtUtils jwtUtils;
    private final RegistrationTokenRepository registrationTokenRepository;

    @Override
    @Transactional
    public RegistrationTokenResponse createRegistrationToken(UUID id) {

        ShortDepartmentUserInfoDTO shortDepartmentUserInfoDTO = departmentUserClient.getShortUserInfo(id);

        if (!shortDepartmentUserInfoDTO.getRole().equals(DepartmentRole.TEAM_LEADER)) {
            //TODO exception not team lead
        }

        String salt = jwtUtils.generateSalt();

        Key key = jwtUtils.getRegistrationKeyWithSalt(salt);

        String token = Jwts
                .builder()
                .setSubject(shortDepartmentUserInfoDTO.getId().toString())
                .signWith(key)
                .compact();

        RegistrationToken registrationToken = new RegistrationToken(salt, token);

        RegistrationToken savedToken = registrationTokenRepository.save(registrationToken);

        jwtUtils.validateRegistrationToken(savedToken.getToken(), key);

        return new RegistrationTokenResponse(token);
    }

    @Override
    @Transactional
    public UUID parseRegistrationTokenToTeamLeaderId(String token) {
        RegistrationToken registrationToken = registrationTokenRepository.findByToken(token).get();
        //TODO exception
        Key key = jwtUtils.getRegistrationKeyWithSalt(registrationToken.getSalt());
        return UUID.fromString(jwtUtils.getRegistrationClaims(token, key).getSubject());
    }

    @Override
    @Transactional
    public void deleteByToken(String token) {
        registrationTokenRepository.deleteByToken(token);
    }
}
