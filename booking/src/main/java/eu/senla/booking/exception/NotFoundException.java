package eu.senla.booking.exception;

import eu.senla.booking.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

    public NotFoundException(String message, ErrorCode errorStatus) {
        super(errorStatus.getMessage() + message, HttpStatus.NOT_FOUND, errorStatus);
    }
}
