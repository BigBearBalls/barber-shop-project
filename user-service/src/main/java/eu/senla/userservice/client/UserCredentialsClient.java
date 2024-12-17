package eu.senla.userservice.client;

import eu.senla.common.dto.UserCredentialsDTO;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${feign.clients.user-credentials-service.url}", name = "userCredentialsClient")
public interface UserCredentialsClient {

    @GetMapping("/internal/users/")
    UserCredentialsDTO getUserCredentials();

    @GetMapping("/internal/users/{userId}")
    UserCredentialsDTO getUserCredentialsById(@PathVariable("userId") UUID userId);
}
