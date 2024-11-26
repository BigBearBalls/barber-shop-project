package eu.senla.accountservice.client;

import eu.senla.accountservice.configuration.FeignConfig;
import eu.senla.accountservice.dto.UserCredentialsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${feign.clients.user-credentials-service.url}", name = "userCredentialsClient",
        configuration = FeignConfig.class)
public interface UserCredentialsClient {

    @GetMapping("/internal/users/{userEmail}")
    UserCredentialsDTO getUserCredentialsByEmail(@PathVariable String userEmail);
}
