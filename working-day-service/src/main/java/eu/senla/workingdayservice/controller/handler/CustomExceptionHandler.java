package eu.senla.workingdayservice.controller.handler;

import eu.senla.workingdayservice.exception.*;
import eu.senla.workingdayservice.util.ExceptionInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundByDateException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundByDateException(HttpServletRequest request, AbstractException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponseBuilder(exception.getCode(), exception.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(NotFoundByIdException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundByIdException(HttpServletRequest request, AbstractException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponseBuilder(exception.getCode(), exception.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(NotFoundByDateAndByIdException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundByDateAndByIdException(HttpServletRequest request, AbstractException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponseBuilder(exception.getCode(), exception.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(EntityExistException.class)
    public ResponseEntity<ExceptionResponse> handleEntityExistException(HttpServletRequest request, AbstractException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionResponseBuilder(exception.getCode(), exception.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleEntityExistException(HttpServletRequest request, Exception exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionResponseBuilder(ExceptionInfo.HANDLER_EXCEPTION.getExceptionCode(),
                        ExceptionInfo.HANDLER_EXCEPTION.getExceptionMessage(), request.getRequestURI()));
    }

    private ExceptionResponse exceptionResponseBuilder(String errorCode, String message, String path) {
        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(errorCode)
                .message(message)
                .path(path)
                .build();
    }

}
