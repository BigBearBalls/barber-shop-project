package eu.senla.gatewayservice.client;

import eu.senla.gatewayservice.dto.UserCredentialsByAccessToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${feign.clients.auth-service.url}", name = "authClient")
public interface AuthClient {

    @GetMapping("/internal/jwt/access")
    UserCredentialsByAccessToken getUserCredentialsByAccessToken(@RequestParam String accessToken);
}
