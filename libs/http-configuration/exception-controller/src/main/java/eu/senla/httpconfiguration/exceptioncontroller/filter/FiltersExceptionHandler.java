package eu.senla.httpconfiguration.exceptioncontroller.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.common.constant.SecurityConstants;
import eu.senla.common.enums.ErrorCode;
import eu.senla.httpconfiguration.exceptioncontroller.dto.ExceptionResponse;
import eu.senla.httpconfiguration.exceptioncontroller.exception.AuthenticationException;
import eu.senla.httpconfiguration.exceptioncontroller.exception.FilterException;
import eu.senla.libs.common.serialization.configuration.ObjectMapperConfiguration;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

import static eu.senla.httpconfiguration.core.util.ExceptionWrapper.buildExceptionResponse;

@Component
@RequiredArgsConstructor
@Slf4j
@Import(ObjectMapperConfiguration.class)
public class FiltersExceptionHandler extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (FilterException e) {
            ExceptionResponse exceptionResponse = extractFilterExceptionInExceptionResponse(e, request.getRequestURI());
            makeHttpServletResponseWithExceptionResponse(response, e.getStatus().value(), exceptionResponse);
        } /*catch (FeignException e) {
            HttpStatus httpStatus = HttpStatus.valueOf(e.status() != 0 ? e.status() : HttpStatus.I_AM_A_TEAPOT.value());
            ExceptionResponse exceptionResponse = extractFeignHttpResponseBodyToExceptionResponseObject(e, request);
            makeHttpServletResponseWithExceptionResponse(response, httpStatus.value(), exceptionResponse);
        }*/ catch (AuthenticationException e) {
            HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
            ExceptionResponse exceptionResponse = buildExceptionResponse(e, request.getRequestURI());
            makeHttpServletResponseWithExceptionResponse(response, httpStatus.value(), exceptionResponse);
        } catch (NoResourceFoundException e) {
            HttpStatus httpStatus = HttpStatus.NOT_FOUND;
            ExceptionResponse exceptionResponse = buildExceptionResponse(ErrorCode.ERR_NO_RESOURCE,
                    ErrorCode.ERR_NO_RESOURCE.getMessage(), request.getRequestURI());
            makeHttpServletResponseWithExceptionResponse(response, httpStatus.value(), exceptionResponse);
        } catch (Exception e) {
            if (!response.isCommitted()) {
                ExceptionResponse exceptionResponse = extractExceptionInExceptionResponse(e, request.getRequestURI());
                makeHttpServletResponseWithExceptionResponse(response, HttpStatus.I_AM_A_TEAPOT.value(), exceptionResponse);
            }
        }
    }

/*    private ExceptionResponse extractFeignHttpResponseBodyToExceptionResponseObject(FeignException e,
                                                                                    HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ErrorCode.ERR_UNKNOWN_CODE,
                ErrorCode.ERR_UNKNOWN_CODE.getMessage(), request.getRequestURI());
        try {
            Optional<ByteBuffer> responseBody = e.responseBody();
            if (responseBody.isPresent()) {
                String body = StandardCharsets.UTF_8.decode(responseBody.get()).toString();
                exceptionResponse = objectMapper.readValue(body, ExceptionResponse.class);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return exceptionResponse;
    }*/

    private ExceptionResponse extractFilterExceptionInExceptionResponse(FilterException e, String path) {
        return new ExceptionResponse(
                LocalDateTime.now(),
                e.getErrorCode(),
                e.getMessage(),
                path
        );
    }

    private ExceptionResponse extractExceptionInExceptionResponse(Exception e, String path) {
        return new ExceptionResponse(
                LocalDateTime.now(),
                ErrorCode.ERR_UNKNOWN_CODE,
                String.format(SecurityConstants.EXCEPTION_MESSAGE_TEMPLATE, e.getClass().getSimpleName(), e.getMessage()),
                path
        );
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
