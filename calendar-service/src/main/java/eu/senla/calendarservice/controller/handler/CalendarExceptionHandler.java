package eu.senla.calendarservice.controller.handler;


import eu.senla.calendarservice.dto.ErrorResponse;
import eu.senla.calendarservice.exception.EmptyDateException;
import eu.senla.calendarservice.exception.InvalidDateException;
import eu.senla.calendarservice.util.constants.ErrorConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CalendarExceptionHandler {


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleBadDateFormat(HttpServletRequest request){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseBuilder(HttpStatus.BAD_REQUEST, ErrorConstants.MISMATCH_EXCEPTION_ERROR_MESSAGE ,ErrorConstants.MISMATCH_EXCEPTION_ERROR_CODE, request.getRequestURI()));
}
    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDate(HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseBuilder(HttpStatus.BAD_REQUEST, ErrorConstants.INVALID_DATE_ERROR_MESSAGE ,ErrorConstants.INVALID_DATE_ERROR_CODE, request.getRequestURI()));
    }

    @ExceptionHandler(EmptyDateException.class)
    public ResponseEntity<ErrorResponse> handleEmptyDate(HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseBuilder(HttpStatus.BAD_REQUEST, ErrorConstants.EMPTY_DATE_ERROR_MESSAGE ,ErrorConstants.EMPTY_DATE_ERROR_CODE, request.getRequestURI()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDateIsAlreadyExist(HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseBuilder(HttpStatus.BAD_REQUEST, ErrorConstants.DATE_IS_ALREADY_EXIST_ERROR_MESSAGE ,ErrorConstants.DATE_IS_ALREADY_EXIST_ERROR_CODE, request.getRequestURI()));
    }

    private ErrorResponse errorResponseBuilder(HttpStatus status, String message, String errorCode, String path){
        return ErrorResponse.builder()
                .status(status)
                .message(message)
                .errorCode(errorCode)
                .path(path)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
