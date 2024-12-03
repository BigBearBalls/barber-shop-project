package eu.senla.common.exception;

import eu.senla.common.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class HeadersParseException extends FilterException {

    public HeadersParseException(ErrorCode errorCode) {
        super(errorCode.getMessage(), HttpStatus.BAD_REQUEST, errorCode);
    }
}
