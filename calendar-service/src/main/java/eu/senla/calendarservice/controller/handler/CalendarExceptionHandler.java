package eu.senla.calendarservice.controller.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.calendarservice.dto.ErrorResponse;
import eu.senla.calendarservice.exception.CalendarApiException;
import eu.senla.calendarservice.util.constants.ErrorConstants;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CalendarExceptionHandler {

    private final ObjectMapper objectMapper;

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

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeignException(FeignException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.valueOf(e.status() != 0 ? e.status() : HttpStatus.INTERNAL_SERVER_ERROR.value());
        ErrorResponse exceptionResponse = new ErrorResponse(LocalDateTime.now(), ErrorConstants.HANDLE_FEIGN_CLIENT_EXCEPTION,
                ErrorConstants.HANDLE_FEIGN_CLIENT_EXCEPTION_MESSAGE, request.getRequestURI());
        try {
            Optional<ByteBuffer> responseBody = e.responseBody();
            if (responseBody.isPresent()) {
                String body = StandardCharsets.UTF_8.decode(responseBody.get()).toString();
                exceptionResponse = objectMapper.readValue(body, ErrorResponse.class);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return ResponseEntity.status(httpStatus).body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponseBuilder(
                ErrorConstants.EXCEPTION_ERROR_MESSAGE, ErrorConstants.EXCEPTION_ERROR_CODE,
                request.getRequestURI()));
    }

    private ErrorResponse errorResponseBuilder(String message, String code, String path) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(code)
                .message(message)
                .path(path)
                .build();
    }

}
