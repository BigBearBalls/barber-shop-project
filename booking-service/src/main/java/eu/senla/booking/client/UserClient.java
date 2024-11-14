package eu.senla.booking.client;

import eu.senla.booking.configuration.FeignConfig;
import eu.senla.booking.data.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(url = "${feign.clients.user-service.url}", name = "userClient", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/{userId}")
    UserDTO getUserById(@PathVariable UUID userId);
}
