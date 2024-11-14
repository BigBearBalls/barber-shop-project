package eu.senla.workingdayservice.controller.handler;

import eu.senla.workingdayservice.exception.EntityExistException;
import eu.senla.workingdayservice.exception.NotFoundByDateException;
import eu.senla.workingdayservice.exception.NotFoundByIdException;
import eu.senla.workingdayservice.util.ExceptionInfo;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundByDateException.class)
    public ResponseEntity<?> handleNotFoundByDateException(HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(ExceptionInfo.WORKING_DAY_NOT_FOUND_BY_DATE.getExceptionMessage(),
                                            ExceptionInfo.WORKING_DAY_NOT_FOUND_BY_DATE.getExceptionCode(),
                                            request.getRequestURI(),
                                            LocalDateTime.now()));
    }

    @ExceptionHandler(NotFoundByIdException.class)
    public ResponseEntity<?> handleNotFoundByIdException(HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(ExceptionInfo.WORKING_DAY_NOT_FOUND_BY_ID.getExceptionMessage(),
                                            ExceptionInfo.WORKING_DAY_NOT_FOUND_BY_ID.getExceptionCode(),
                                            request.getRequestURI(),
                                            LocalDateTime.now()));
    }

    @ExceptionHandler(EntityExistException.class)
    public ResponseEntity<?> handleEntityExistException(HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ExceptionResponse(ExceptionInfo.ENTITY_EXIST.getExceptionMessage(),
                                            ExceptionInfo.ENTITY_EXIST.getExceptionCode(),
                                            request.getRequestURI(),
                                            LocalDateTime.now()));
    }
}
