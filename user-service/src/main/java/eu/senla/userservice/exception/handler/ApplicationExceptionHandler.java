package eu.senla.userservice.exception.handler;

import eu.senla.userservice.dto.exception.ExceptionResponse;
import eu.senla.userservice.dto.exception.Violation;
import eu.senla.userservice.enums.ErrorCode;
import eu.senla.userservice.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(buildExceptionResponse(e, request.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationExceptions(MethodArgumentNotValidException e, HttpServletRequest request) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage())).toList();
        ExceptionResponse exceptionResponse = buildExceptionResponse(ErrorCode.ERR_METHOD_ARGUMENTS_VALIDATION_EXCEPTION,
                violations.toString(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                buildExceptionResponse(ErrorCode.ERR_UNKNOWN_CODE, e.getMessage(), request.getRequestURI()));
    }

    private ExceptionResponse buildExceptionResponse(ApiException e, String uri) {
        return buildExceptionResponse(e.getErrorCode(), e.getMessage(), uri);
    }

    private ExceptionResponse buildExceptionResponse(ErrorCode errorCode, String message, String uri) {
        return new ExceptionResponse(LocalDateTime.now(), errorCode, message, uri);
    }
}
