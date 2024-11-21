package eu.senla.authservice.exception;

import eu.senla.authservice.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends ApiException {
    public AuthorizationException(ErrorCode errorCode) {
        super(errorCode.getMessage(), HttpStatus.FORBIDDEN, errorCode);
    }
}
