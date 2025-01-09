package eu.senla.httpconfiguration.exceptioncontroller.exception;

import eu.senla.httpconfiguration.exceptioncontroller.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class JwtValidateException extends FilterException {

    public JwtValidateException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.UNAUTHORIZED, errorCode);
    }

    public JwtValidateException(ErrorCode errorCode) {
        this(errorCode.getMessage(), errorCode);
    }
}
