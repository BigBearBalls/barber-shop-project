package eu.senla.procedureservice.exception;

import eu.senla.procedureservice.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class ExistsException extends ApiException {
    public ExistsException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.CONFLICT, errorCode);
    }
}
