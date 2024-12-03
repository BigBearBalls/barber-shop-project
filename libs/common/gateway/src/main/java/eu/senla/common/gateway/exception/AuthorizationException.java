package eu.senla.common.gateway.exception;

import eu.senla.common.enums.ErrorCode;
import eu.senla.common.exception.ApiException;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends ApiException {
    public AuthorizationException(ErrorCode errorCode) {
        super(errorCode.getMessage(), HttpStatus.FORBIDDEN, errorCode);
    }
}
