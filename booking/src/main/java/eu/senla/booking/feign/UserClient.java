package eu.senla.booking.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "userClient", url = "${feign.clients.user-client.url}")
public interface UserClient {

    @GetMapping
    public

}
