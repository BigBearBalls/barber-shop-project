package eu.senla.httpconfiguration.exceptioncontroller.exception;

import eu.senla.httpconfiguration.exceptioncontroller.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class FilterException extends ApiException {

    public FilterException(String message, HttpStatus status, ErrorCode errorCode) {
        super(message, status, errorCode);
    }

    public FilterException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
}
