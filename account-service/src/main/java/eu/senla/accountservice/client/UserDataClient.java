package eu.senla.accountservice.client;

import eu.senla.common.dto.UserDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${feign.clients.user-data-service.url}", name = "userDataClient")
public interface UserDataClient {

    @GetMapping(value = "/internal/users/")
    UserDataDTO getUserData();
}
