package eu.senla.booking.controller.handler;

import eu.senla.booking.constant.ErrorConstants;
import eu.senla.booking.data.response.ErrorResponse;
import eu.senla.booking.service.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler({TimeAlreadyBookedException.class, MasterNotWorkException.class, ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFound(HttpServletRequest request, ApplicationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponseBuilder(ErrorConstants.HANDLE_RESOURCE_NOT_FOUND_BAD_REQUEST, ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, ApplicationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponseBuilder(ErrorConstants.HANDLE_EXCEPTION, ex.getMessage(), request.getRequestURI()));
    }

    private ErrorResponse errorResponseBuilder(String errorCode, String message, String path) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(errorCode)
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
