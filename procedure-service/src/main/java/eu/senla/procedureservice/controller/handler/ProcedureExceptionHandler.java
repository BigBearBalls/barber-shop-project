package eu.senla.procedureservice.controller.handler;

import eu.senla.procedureservice.data.dto.exception.ExceptionResponse;
import eu.senla.procedureservice.enums.ErrorCode;
import eu.senla.procedureservice.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ProcedureExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(buildExceptionResponse(ErrorCode.APP_EXCEPTION, e.getMessage(), request.getRequestURI()));
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
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                buildExceptionResponse(ErrorCode.ERR_UNKNOWN_CODE, e.getMessage(), request.getRequestURI()));
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
