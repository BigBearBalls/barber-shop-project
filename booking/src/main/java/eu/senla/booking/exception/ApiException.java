package eu.senla.booking.exception;

import eu.senla.booking.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private final HttpStatus status;
    private final ErrorCode errorStatus;

    public ApiException(String message, HttpStatus status, ErrorCode errorStatus) {
        super(message);
        this.status = status;
        this.errorStatus = errorStatus;
    }
}

