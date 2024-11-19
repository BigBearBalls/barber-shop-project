package eu.senla.authservice.client;

import eu.senla.authservice.configuration.FeignConfig;
import eu.senla.authservice.dto.UserDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(url = "${feign.clients.user-service.url}", name = "userClient", configuration = FeignConfig.class)
public interface UserDataClient {

    @PostMapping(value = "/internal/users/")
    @ResponseStatus(HttpStatus.CREATED)
    void createUser(@RequestBody UserDataDTO userDataDTO);

    @GetMapping(value = "/internal/users/{userId}")
    UserDataDTO getUserById(@PathVariable UUID userId);
}
