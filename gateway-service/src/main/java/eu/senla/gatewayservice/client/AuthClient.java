package eu.senla.gatewayservice.client;

import eu.senla.gatewayservice.dto.AccessTokenExtractedData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${feign.clients.auth-service.url}", name = "authClient")
public interface AuthClient {

    @GetMapping("/internal/jwt/access")
    AccessTokenExtractedData getAccessTokenExtractedData(@RequestParam String accessToken);
}
