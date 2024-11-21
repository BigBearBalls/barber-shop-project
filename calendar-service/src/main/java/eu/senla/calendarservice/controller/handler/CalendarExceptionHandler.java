package eu.senla.calendarservice.controller.handler;


import eu.senla.calendarservice.dto.ErrorResponse;
import eu.senla.calendarservice.exception.CalendarApiException;
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
    public ResponseEntity<ErrorResponse> handleBadDateFormat(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseBuilder(
                ErrorConstants.MISMATCH_EXCEPTION_ERROR_MESSAGE, ErrorConstants.MISMATCH_EXCEPTION_ERROR_CODE,
                request.getRequestURI()));

    }

    @ExceptionHandler(CalendarApiException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDate(HttpServletRequest request, CalendarApiException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseBuilder(
                exception.getErrorMessage(), exception.getErrorCode(),
                request.getRequestURI()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDateIsAlreadyExist(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseBuilder(
                ErrorConstants.DATE_IS_ALREADY_EXIST_ERROR_MESSAGE, ErrorConstants.DATE_IS_ALREADY_EXIST_ERROR_CODE,
                request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponseBuilder(
                ErrorConstants.EXCEPTION_ERROR_MESSAGE, ErrorConstants.EXCEPTION_ERROR_CODE,
                request.getRequestURI()));
    }

    private ErrorResponse errorResponseBuilder(String message, String errorCode, String path) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(errorCode)
                .message(message)
                .path(path)
                .build();
    }

}
