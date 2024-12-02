package eu.senla.httpconfiguration.exception;

import eu.senla.httpconfiguration.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class HeadersParseException extends FilterException {

    public HeadersParseException(ErrorCode errorCode) {
        super(errorCode.getMessage(), HttpStatus.BAD_REQUEST, errorCode);
    }
}
