package eu.senla.authservice.client;

import eu.senla.common.dto.UserDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(url = "${feign.clients.user-service.url}", name = "userClient")
public interface UserDataClient {

    @PostMapping(value = "/internal/users/")
    @ResponseStatus(HttpStatus.CREATED)
    void createUser(@RequestBody UserDataDTO userDataDTO);

    @GetMapping(value = "/internal/users/")
    UserDataDTO getUserData();
}
