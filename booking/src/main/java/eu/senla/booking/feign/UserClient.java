package eu.senla.booking.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "userClient", url = "${feign.clients.user-client.url}")
public interface UserClient {

}
