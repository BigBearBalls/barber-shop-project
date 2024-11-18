package eu.senla.procedureservice.exception;

import eu.senla.procedureservice.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class FilterException extends ApiException {

    public FilterException(String message, HttpStatus status, ErrorCode errorCode) {
        super(message, status, errorCode);
    }

    public FilterException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
}
