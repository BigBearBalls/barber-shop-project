package eu.senla.authservice.repository;

import eu.senla.authservice.model.RegistrationToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, String> {

    Optional<RegistrationToken> findByToken(String token);

    void deleteByToken(String token);
}
