package eu.senla.gatewayservice.filters;

import eu.senla.gatewayservice.model.User;
import org.springframework.cloud.gateway.server.mvc.common.MvcUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.UUID;

@Component
public class UserIdHeaderInterceptorFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {

    @Override
    public ServerResponse filter(ServerRequest request, HandlerFunction<ServerResponse> next) throws Exception {
        ServerRequest requestToUse = request;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String headerName = "X-User-Id";
            UUID userId = ((User) authentication.getPrincipal()).getId();
            String expandedValues = MvcUtils.expand(request, userId.toString());
            requestToUse = ServerRequest.from(request).header(headerName, expandedValues).build();
        }
        return next.handle(requestToUse);
    }
}
