package eu.senla.workingdayservice.controller.handler;

import eu.senla.workingdayservice.exception.AbstractException;
import eu.senla.workingdayservice.exception.EntityExistException;
import eu.senla.workingdayservice.exception.NotFoundByDateAndByIdException;
import eu.senla.workingdayservice.exception.NotFoundByDateException;
import eu.senla.workingdayservice.exception.NotFoundByIdException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundByDateException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundByDateException(HttpServletRequest request, AbstractException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(exception.getMessage(),
                                            exception.getCode(),
                                            request.getRequestURI(),
                                            LocalDateTime.now()));
    }

    @ExceptionHandler(NotFoundByIdException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundByIdException(HttpServletRequest request, AbstractException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(exception.getMessage(),
                                            exception.getCode(),
                                            request.getRequestURI(),
                                            LocalDateTime.now()));
    }

    @ExceptionHandler(NotFoundByDateAndByIdException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundByDateAndByIdException(HttpServletRequest request, AbstractException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(exception.getMessage(),
                                            exception.getCode(),
                                            request.getRequestURI(),
                                            LocalDateTime.now()));
    }

    @ExceptionHandler(EntityExistException.class)
    public ResponseEntity<ExceptionResponse> handleEntityExistException(HttpServletRequest request, AbstractException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ExceptionResponse(exception.getMessage(),
                                            exception.getCode(),
                                            request.getRequestURI(),
                                            LocalDateTime.now()));
    }
}
