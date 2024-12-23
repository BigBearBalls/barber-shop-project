package eu.senla.httpconfiguration.exceptioncontroller.exception.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.common.enums.ErrorCode;
import eu.senla.httpconfiguration.exceptioncontroller.dto.ExceptionResponse;
import eu.senla.httpconfiguration.exceptioncontroller.dto.Violation;
import eu.senla.httpconfiguration.exceptioncontroller.exception.ApiException;
import eu.senla.httpconfiguration.exceptioncontroller.exception.AuthenticationException;
import eu.senla.httpconfiguration.exceptioncontroller.util.ExceptionWrapper;
import eu.senla.libs.common.serialization.configuration.ObjectMapperConfiguration;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
@Import({ObjectMapperConfiguration.class})
public class WebExceptionHandler {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(ExceptionWrapper.buildExceptionResponse(e, request.getRequestURI()));
    }

/*    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeignException(FeignException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.valueOf(e.status() > 0 ? e.status() : HttpStatus.SERVICE_UNAVAILABLE.value());
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
        return ResponseEntity.status(httpStatus).body(exceptionResponse);
    }*/

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationExceptions(MethodArgumentNotValidException e, HttpServletRequest request) throws JsonProcessingException {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage())).toList();
        ExceptionResponse exceptionResponse = ExceptionWrapper.buildExceptionResponse(ErrorCode.ERR_METHOD_ARGUMENTS_VALIDATION_EXCEPTION,
                objectMapper.writeValueAsString(violations), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<?> handleMissingRequestHeaderException(MissingRequestHeaderException e, HttpServletRequest r) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionWrapper.buildExceptionResponse(
                ErrorCode.ERR_MISSING_HEADER, String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()),
                r.getRequestURI()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e,
                                                                   HttpServletRequest request) {
        ExceptionResponse response = ExceptionWrapper.buildExceptionResponse(ErrorCode.ERR_JSON_PARSE_EXCEPTION,
                ErrorCode.ERR_JSON_PARSE_EXCEPTION.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> handleAuthenticationException(AuthenticationException e,
                                                                           HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(ExceptionWrapper.buildExceptionResponse(e, request.getRequestURI()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e,
                                                                          HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionWrapper.buildExceptionResponse(ErrorCode.ERR_HTTP_METHOD_NOT_ALLOWED,
                ErrorCode.ERR_HTTP_METHOD_NOT_ALLOWED.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
                                                                       HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionWrapper.buildExceptionResponse(ErrorCode.ERR_METHOD_ARGUMENTS_TYPE_MISMATCH,
                ErrorCode.ERR_METHOD_ARGUMENTS_TYPE_MISMATCH.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFoundExceptionException(NoResourceFoundException e,
                                                                       HttpServletRequest request) {
        ExceptionResponse exceptionResponse = ExceptionWrapper.buildExceptionResponse(ErrorCode.ERR_NO_RESOURCE,
                ErrorCode.ERR_NO_RESOURCE.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e, HttpServletRequest request) {
        log.error(e.getClass().getName(), e);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(
                ExceptionWrapper.buildExceptionResponse(ErrorCode.ERR_UNKNOWN_CODE, e.getMessage(), request.getRequestURI()));
    }
}
