package eu.senla.httpconfiguration.exceptioncontroller.exception;

import eu.senla.common.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class ExistsException extends ApiException {
    public ExistsException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.CONFLICT, errorCode);
    }

    public ExistsException(ErrorCode errorCode) {
        super(errorCode.getMessage(), HttpStatus.CONFLICT, errorCode);
    }
}
