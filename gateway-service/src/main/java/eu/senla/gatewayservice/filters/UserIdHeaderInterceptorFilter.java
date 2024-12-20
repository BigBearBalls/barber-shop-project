package eu.senla.gatewayservice.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.httpconfiguration.security.dto.UserDTO;
import eu.senla.httpconfiguration.security.holder.UserHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.server.mvc.common.MvcUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
@RequiredArgsConstructor
public class UserIdHeaderInterceptorFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {

    private final ObjectMapper objectMapper;

    @Override
    public ServerResponse filter(ServerRequest request, HandlerFunction<ServerResponse> next) throws Exception {
        ServerRequest requestToUse = request;
        if (UserHolder.userExists()) {
            String headerName = "X-User";
            UserDTO userDTO = UserHolder.getUser();
            String expandedValues = MvcUtils.expand(request, objectMapper.writeValueAsString(userDTO));
            requestToUse = ServerRequest.from(request).header(headerName, expandedValues).build();
        }
        return next.handle(requestToUse);
    }
}
