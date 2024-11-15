package eu.senla.userservice.exception;

import eu.senla.userservice.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class ExistsException extends ApiException {
    public ExistsException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.CONFLICT, errorCode);
    }
}
