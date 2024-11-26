package eu.senla.procedureservice.controller.advice;

import eu.senla.procedureservice.data.dto.exception.ExceptionResponse;
import eu.senla.procedureservice.enums.ErrorCode;
import eu.senla.procedureservice.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ProcedureExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(buildExceptionResponse(ErrorCode.ERR_UNKNOWN_CODE, e.getMessage(), request.getRequestURI()));
    }

//    @ExceptionHandler(AuthorizationDeniedException.class)
//    public ResponseEntity<?> handleAuthorizationException(AuthorizationDeniedException e, HttpServletRequest request) {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(
//                LocalDateTime.now(),
//                ErrorCode.ERR_ACCESS_DENIED,
//                e.getMessage(),
//                request.getRequestURI()
//        ));
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                buildExceptionResponse(ErrorCode.ERR_UNKNOWN_CODE, e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        String errorMessages = e.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                .orElse("Validation error occurred");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                buildExceptionResponse(ErrorCode.ERR_VALIDATION, errorMessages, request.getRequestURI()));
    }

    private ExceptionResponse buildExceptionResponse(ErrorCode errorCode, String message, String path) {
        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(errorCode)
                .message(message)
                .path(path)
                .build();
    }

}
