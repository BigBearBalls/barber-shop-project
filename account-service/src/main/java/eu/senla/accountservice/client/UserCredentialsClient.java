package eu.senla.accountservice.client;

import eu.senla.accountservice.dto.UserCredentialsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${feign.clients.user-credentials-service.url}", name = "userCredentialsClient")
public interface UserCredentialsClient {

    @GetMapping("/internal/users/")
    UserCredentialsDTO getUserCredentials();
}
