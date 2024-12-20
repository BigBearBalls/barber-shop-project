package eu.senla.booking.client;

import eu.senla.common.dto.UserDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${feign.clients.user-service.url}", name = "userClient")
public interface UserClient {

    @GetMapping("/internal/users/")
    UserDataDTO getUser();

}
