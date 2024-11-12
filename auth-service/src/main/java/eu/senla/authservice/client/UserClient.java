package eu.senla.authservice.client;

import eu.senla.authservice.dto.RegistrationRequest;
import eu.senla.authservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(url = "${feign.clients.user-service.url}", name = "userClient")
public interface UserClient {

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDTO getUserByEmail(@RequestParam("email") String email);

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    void createUser(@RequestBody RegistrationRequest request);

    @GetMapping(value = "/{userId}/password")
    String getUserPasswordById(@PathVariable UUID userId);
}
