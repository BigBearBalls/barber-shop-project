package eu.senla.workingdayservice.controller.handler;

import eu.senla.workingdayservice.exception.AbstractException;
import eu.senla.workingdayservice.exception.EntityExistException;
import eu.senla.workingdayservice.exception.NotFoundByDateAndByIdException;
import eu.senla.workingdayservice.exception.NotFoundByDateException;
import eu.senla.workingdayservice.exception.NotFoundByIdException;
import eu.senla.workingdayservice.util.ExceptionInfo;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String fieldErrors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                .orElse("");
        String globalErrors = e.getBindingResult().getGlobalErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                .orElse("");
        String errorMessages = (fieldErrors + " " + globalErrors).trim();
        if (errorMessages.endsWith(";")) {
            errorMessages = errorMessages.substring(0, errorMessages.length() - 1);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                exceptionResponseBuilder(ExceptionInfo.VALIDATION_ERROR.getExceptionCode(), errorMessages, request.getRequestURI()));
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionResponse> handleEntityExistException(HttpServletRequest request, Exception exception) {
//        return ResponseEntity
//                .status(HttpStatus.CONFLICT)
//                .body(exceptionResponseBuilder(ExceptionInfo.HANDLER_EXCEPTION.getExceptionCode(),
//                        ExceptionInfo.HANDLER_EXCEPTION.getExceptionMessage(), request.getRequestURI()));
//    }

    private ExceptionResponse exceptionResponseBuilder(String code, String message, String path){
        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(code)
                .message(message)
                .path(path)
                .build();
    }

}
