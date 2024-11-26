package eu.senla.accountservice.client;

import eu.senla.accountservice.configuration.FeignConfig;
import eu.senla.accountservice.dto.UserDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(url = "${feign.clients.user-data-service.url}", name = "userDataClient", configuration = FeignConfig.class)
public interface UserDataClient {

    @GetMapping(value = "/internal/users/{userId}")
    UserDataDTO getUserDataById(@PathVariable UUID userId);
}
