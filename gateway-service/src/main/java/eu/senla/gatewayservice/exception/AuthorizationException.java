package eu.senla.gatewayservice.exception;

import eu.senla.gatewayservice.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends ApiException {
    public AuthorizationException(ErrorCode errorCode) {
        super(errorCode.getMessage(), HttpStatus.FORBIDDEN, errorCode);
    }
}
