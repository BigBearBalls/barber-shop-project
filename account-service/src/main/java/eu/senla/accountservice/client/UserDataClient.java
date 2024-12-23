package eu.senla.accountservice.client;

import eu.senla.common.user.dto.UserDataDTO;
import eu.senla.common.user.dto.UsersDataResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(url = "${feign.clients.user-data-service.url}", name = "userDataClient")
public interface UserDataClient {

    @GetMapping(value = "/internal/users/")
    UserDataDTO getUserData();

    @GetMapping(value = "/internal/users/{userId}")
    UserDataDTO getUserDataById(@PathVariable("userId") UUID userId);

    @GetMapping(value = "/internal/users/search")
    UsersDataResponse searchUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName);
}
