package eu.senla.booking.client;

import eu.senla.common.auth.dto.UserCredentialsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(url = "${feign.clients.auth-service.url}", name = "authClient")
public interface AuthClient {

    @GetMapping("internal/users/{userId}")
    UserCredentialsDTO getUserById(@PathVariable UUID userId);
}
