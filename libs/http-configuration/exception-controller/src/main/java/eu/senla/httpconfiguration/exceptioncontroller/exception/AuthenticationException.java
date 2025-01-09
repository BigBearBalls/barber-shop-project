package eu.senla.httpconfiguration.exceptioncontroller.exception;

import eu.senla.httpconfiguration.exceptioncontroller.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends ApiException {
    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode.getMessage(), HttpStatus.UNAUTHORIZED, errorCode);
    }
}
