package eu.senla.gatewayservice.exception;

import eu.senla.gatewayservice.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class JwtValidateException extends FilterException {

    public JwtValidateException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.UNAUTHORIZED, errorCode);
    }

    public JwtValidateException(ErrorCode errorCode) {
        this(errorCode.getMessage(), errorCode);
    }
}
