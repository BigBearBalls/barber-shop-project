package eu.senla.procedureservice.exception;

import eu.senla.procedureservice.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.NOT_FOUND, errorCode);
    }

    public NotFoundException(ErrorCode errorCode) {
        this(errorCode.getMessage(), errorCode);
    }
}
