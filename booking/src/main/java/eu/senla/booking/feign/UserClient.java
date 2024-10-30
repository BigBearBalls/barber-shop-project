package eu.senla.booking.feign;

import eu.senla.booking.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "userClient", url = "${feign.clients.user-client.url}")
public interface UserClient {

    @GetMapping(value = "${feign.clients.user-client.get-user.url}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDTO getUser(@PathVariable("userId") UUID userId);
}
