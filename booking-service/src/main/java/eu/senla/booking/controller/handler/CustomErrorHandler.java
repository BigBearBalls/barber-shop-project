package eu.senla.booking.controller.handler;

import eu.senla.booking.dto.response.ErrorResponse;
import eu.senla.booking.service.exception.ResourceNotFoundException;
import eu.senla.booking.service.exception.TimeAlreadyBookedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomErrorHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.toString(),
                ex.getMessage(), HttpStatus.NOT_FOUND.value());
        log.warn(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(TimeAlreadyBookedException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleResourceNotFound(TimeAlreadyBookedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        log.warn(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
