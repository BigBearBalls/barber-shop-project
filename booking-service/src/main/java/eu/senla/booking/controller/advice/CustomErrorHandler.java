package eu.senla.booking.controller.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.booking.constant.ErrorConstants;
import eu.senla.booking.data.response.ErrorResponse;
import eu.senla.booking.service.exception.ApplicationException;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;


@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CustomErrorHandler {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(HttpServletRequest request, ApplicationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponseBuilder(ErrorConstants.HANDLE_RESOURCE_NOT_FOUND_BAD_REQUEST, ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String errorMessages = e.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .reduce((msg1, msg2) -> msg1 + "; " + msg2)
                .orElse("Validation error occurred");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                errorResponseBuilder(ErrorConstants.HANDLE_UNKNOWN_EXCEPTION, errorMessages, request.getRequestURI()));
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
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, ApplicationException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(errorResponseBuilder(ErrorConstants.HANDLE_UNKNOWN_EXCEPTION, ex.getMessage(), request.getRequestURI()));
    }

    private ErrorResponse errorResponseBuilder(String errorCode, String message, String path) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(errorCode)
                .message(message)
                .path(path)
                .build();
    }

//    @ExceptionHandler(FeignException.class)
//    public ResponseEntity<?> handleFeignException(FeignException e, HttpServletRequest request) {
//        HttpStatus httpStatus = HttpStatus.valueOf(e.status() != 0 ? e.status() : HttpStatus.INTERNAL_SERVER_ERROR.value());
//        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ErrorCode.ERR_UNKNOWN_CODE,
//                ErrorCode.ERR_UNKNOWN_CODE.getMessage(), request.getRequestURI());
//        try {
//            Optional<ByteBuffer> responseBody = e.responseBody();
//            if (responseBody.isPresent()) {
//                String body = StandardCharsets.UTF_8.decode(responseBody.get()).toString();
//                exceptionResponse = objectMapper.readValue(body, ExceptionResponse.class);
//            }
//        } catch (Exception ex) {
//            log.error(ex.getMessage(), ex);
//        }
//        return ResponseEntity.status(httpStatus).body(exceptionResponse);
//    }

}
