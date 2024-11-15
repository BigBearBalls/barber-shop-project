package eu.senla.userservice.exception;

import eu.senla.userservice.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class HeadersParseException extends FilterException {

    public HeadersParseException(ErrorCode errorCode) {
        super(errorCode.getMessage(), HttpStatus.BAD_REQUEST, errorCode);
    }
}
