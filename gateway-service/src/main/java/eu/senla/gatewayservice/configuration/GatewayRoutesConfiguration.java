package eu.senla.gatewayservice.configuration;

import eu.senla.gatewayservice.filters.UserIdHeaderInterceptorFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class GatewayRoutesConfiguration {

    private final UserIdHeaderInterceptorFilter userIdHeaderInterceptorFilter;

//    private final GatewayMvcProperties properties;
//
//    @Bean
//    public RouterFunction<ServerResponse> route() {
//        return RouterFunctions
//                .route()
//                .GET("/api/v1/auth/**", request -> forwardTo("http://auth-service:8080"))
//                .GET("/api/v1/permissions/**", request -> forwardTo("http://auth-service:8080"))
//                .GET("/api/v1/bookings/**", request -> forwardTo("http://booking-service:8080"))
//                .GET("/api/v1/procedures/**", request -> forwardTo("http://procedure-service:8080"))
//                .GET("/api/v1/user/**", request -> forwardTo("http://user-service:8080"))
//                .GET("/api/v1/calendars/**", request -> forwardTo("http://calendar-service:8080"))
//                .GET("/api/v1/working-days/**", request -> forwardTo("http://working-day-service:8080"))
//                .GET("/api/v1/account/**", request -> forwardTo("http://account-service:8080"))
//                .build();
//    }
//
//    private ServerResponse forwardTo(String uri) {
//        return ServerResponse.ok()
//                .header("X-Request-Api-Key", System.getenv("API_KEY"))
//                .header("X-Request-Source", "EXTERNAL")
//                .body("Forwarding to: " + uri);
//    }

    @Bean
    public RouterFunction<ServerResponse> globalRouterFunction(RouterFunction<ServerResponse> originalRouterFunction) {
        return originalRouterFunction.filter(userIdHeaderInterceptorFilter);
    }
}
