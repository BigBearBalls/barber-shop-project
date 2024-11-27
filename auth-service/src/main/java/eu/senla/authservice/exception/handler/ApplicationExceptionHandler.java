package eu.senla.authservice.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.authservice.dto.exception.ExceptionResponse;
import eu.senla.authservice.dto.exception.Violation;
import eu.senla.authservice.enums.ErrorCode;
import eu.senla.authservice.exception.ApiException;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApplicationExceptionHandler {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(buildExceptionResponse(e, request.getRequestURI()));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeignException(FeignException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.valueOf(e.status() != 0 ? e.status() : HttpStatus.SERVICE_UNAVAILABLE.value());
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
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationExceptions(MethodArgumentNotValidException e, HttpServletRequest request) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage())).toList();
        ExceptionResponse exceptionResponse = buildExceptionResponse(ErrorCode.ERR_METHOD_ARGUMENTS_VALIDATION_EXCEPTION,
                violations.toString(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e,
                                                                   HttpServletRequest request) {

        ExceptionResponse response = buildExceptionResponse(ErrorCode.ERR_JSON_PARSE_EXCEPTION,
                ErrorCode.ERR_JSON_PARSE_EXCEPTION.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e, HttpServletRequest request) {
        log.error(e.getClass().getName(), e);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                buildExceptionResponse(ErrorCode.ERR_UNKNOWN_CODE, e.getMessage(), request.getRequestURI()));
    }

    private ExceptionResponse buildExceptionResponse(ApiException e, String uri) {
        return buildExceptionResponse(e.getErrorCode(), e.getMessage(), uri);
    }

    private ExceptionResponse buildExceptionResponse(ErrorCode errorCode, String message, String uri) {
        return new ExceptionResponse(LocalDateTime.now(), errorCode, message, uri);
    }
}
