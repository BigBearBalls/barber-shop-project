package eu.senla.gatewayservice.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.httpconfiguration.exceptioncontroller.enums.ErrorCode;
import eu.senla.httpconfiguration.exceptioncontroller.dto.ExceptionResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static eu.senla.httpconfiguration.exceptioncontroller.util.ExceptionWrapper.buildExceptionResponse;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        ExceptionResponse exceptionResponse = buildExceptionResponse(ErrorCode.ERR_ACCESS_DENIED,
                accessDeniedException.getMessage(), request.getRequestURI());
        makeHttpServletResponseWithExceptionResponse(response, HttpStatus.FORBIDDEN.value(), exceptionResponse);
    }

    private String convertExceptionResponseToString(ExceptionResponse e) throws JsonProcessingException {
        return objectMapper.writeValueAsString(e);
    }

    private void makeHttpServletResponseWithExceptionResponse(HttpServletResponse response, int status,
                                                              ExceptionResponse exResponse) throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().print(convertExceptionResponseToString(exResponse));
        response.getWriter().flush();
    }
}
