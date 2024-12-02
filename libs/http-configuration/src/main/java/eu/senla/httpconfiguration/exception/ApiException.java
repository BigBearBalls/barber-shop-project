package eu.senla.httpconfiguration.exception;

import eu.senla.httpconfiguration.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private final HttpStatus status;

    private final ErrorCode errorCode;

    public ApiException(String message, HttpStatus status, ErrorCode errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

    public ApiException(String message, HttpStatus status) {
        this(message, status, ErrorCode.ERR_UNKNOWN_CODE);
    }

    public ApiException(String message, ErrorCode errorCode) {
        this(message, HttpStatus.BAD_REQUEST, errorCode);
    }

    public ApiException(String message) {
        this(message, HttpStatus.BAD_REQUEST, ErrorCode.ERR_UNKNOWN_CODE);
    }
}
