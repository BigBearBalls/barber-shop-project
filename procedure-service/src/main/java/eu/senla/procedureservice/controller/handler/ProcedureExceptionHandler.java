package eu.senla.procedureservice.controller.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.procedureservice.data.dto.exception.ExceptionResponse;
import eu.senla.procedureservice.enums.ErrorCode;
import eu.senla.procedureservice.exception.ApiException;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ProcedureExceptionHandler {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(buildExceptionResponse(e.getErrorCode(),
                e.getMessage(), request.getRequestURI()));
    }

//    @ExceptionHandler(AuthorizationDeniedException.class)
//    public ResponseEntity<?> handleAuthorizationException(AuthorizationDeniedException e, HttpServletRequest request) {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(
//                LocalDateTime.now(),
//                ErrorCode.ERR_ACCESS_DENIED,
//                e.getMessage(),
//                request.getRequestURI()
//        ));
//    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeignException(FeignException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.valueOf(e.status() != 0 ? e.status() : HttpStatus.INTERNAL_SERVER_ERROR.value());
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ErrorCode.ERR_UNKNOWN_CODE,
                ErrorCode.ERR_UNKNOWN_CODE.getMessage(), request.getRequestURI());
        try {
            Optional<ByteBuffer> responseBody = e.responseBody();
            if (responseBody.isPresent()) {
                String body = StandardCharsets.UTF_8.decode(responseBody.get()).toString();
                exceptionResponse = objectMapper.readValue(body, ExceptionResponse.class);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return ResponseEntity.status(httpStatus).body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                buildExceptionResponse(ErrorCode.ERR_UNKNOWN_CODE, e.getMessage(), request.getRequestURI()));
    }

    private ExceptionResponse buildExceptionResponse(ErrorCode errorCode, String message, String path) {
        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(errorCode)
                .message(message)
                .path(path)
                .build();
    }
}
