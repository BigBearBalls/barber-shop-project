package eu.senla.procedureservice.exception;

import eu.senla.procedureservice.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class HeadersParseException extends FilterException {

    public HeadersParseException(ErrorCode errorCode) {
        super(errorCode.getMessage(), HttpStatus.BAD_REQUEST, errorCode);
    }
}
