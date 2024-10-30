package eu.senla.booking.controller;

import eu.senla.booking.dto.exception.ExceptionResponse;
import eu.senla.booking.dto.exception.Violation;
import eu.senla.booking.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> apiExceptions(ApiException e, HttpServletRequest request) {
        return buildExceptionResponse(e.getStatus(), e, request.getRequestURI());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> noResourceFoundException(NoResourceFoundException e, HttpServletRequest request) {
        return buildExceptionResponse(e.getStatusCode(), e, request.getRequestURI());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e,
                                                                    HttpServletRequest request) {
        return buildExceptionResponse(e.getStatusCode(), e, request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
                                                                 HttpServletRequest request) {
        return buildExceptionResponse(HttpStatus.BAD_REQUEST, e, request.getRequestURI());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> missingServletRequestParameterException(MissingServletRequestParameterException e,
                                                                     HttpServletRequest request) {
        return buildExceptionResponse(e.getStatusCode(), e, request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationExceptions(MethodArgumentNotValidException e, HttpServletRequest request) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage())).toList();
        return buildExceptionResponse(e.getStatusCode(), violations.toString(), request.getRequestURI());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintValidationException(ConstraintViolationException e, HttpServletRequest request) {
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(
                        violation -> new Violation(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .toList();
        return buildExceptionResponse(HttpStatus.BAD_REQUEST, violations.toString(), request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptions(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return buildExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, e, request.getRequestURI());
    }

    public ResponseEntity<Object> buildExceptionResponse(HttpStatusCode code, Exception e, String uri) {
        return buildExceptionResponse(code, e.getMessage(), uri);
    }

    public ResponseEntity<Object> buildExceptionResponse(HttpStatusCode code, String message, String uri) {
        return ResponseEntity.status(code).body(new ExceptionResponse(message, code.value(), LocalDateTime.now(), uri));
    }
}
