package eu.senla.booking.controller.handler;

import eu.senla.booking.data.response.ErrorResponse;
import eu.senla.booking.service.exception.*;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

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

    @ExceptionHandler({TimeAlreadyBookedException.class, MasterNotWorkException.class})
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ApplicationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        log.warn(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
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
