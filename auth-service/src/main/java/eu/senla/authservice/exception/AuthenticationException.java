package eu.senla.authservice.exception;

import eu.senla.authservice.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends ApiException {
    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode.getMessage(), HttpStatus.UNAUTHORIZED, errorCode);
    }
}
