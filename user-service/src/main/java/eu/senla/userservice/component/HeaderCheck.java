package eu.senla.userservice.component;

import eu.senla.userservice.constants.SecurityConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HeaderCheck {

    public boolean hasRequiredHeader(HttpServletRequest request) {
        return request.getHeader(SecurityConstants.REQUEST_SOURCE_HEADER) != null && Objects.equals(
                request.getHeader(SecurityConstants.REQUEST_SOURCE_HEADER), SecurityConstants.INTERNAL_REQUEST_SOURCE);
    }
}
