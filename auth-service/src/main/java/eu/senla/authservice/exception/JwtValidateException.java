package eu.senla.authservice.exception;

import eu.senla.authservice.enums.ErrorCode;

public class JwtValidateException extends ApiException {
    public JwtValidateException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public JwtValidateException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
}
