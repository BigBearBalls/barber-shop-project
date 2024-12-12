package eu.senla.booking.client;

import eu.senla.common.dto.UserDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(url = "${feign.clients.user-service.url}", name = "userClient")
public interface UserClient {

//    @GetMapping("internal/users/{userId}")
//    UserDataDTO getUserById(@PathVariable UUID userId);
}
