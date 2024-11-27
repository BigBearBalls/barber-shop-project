package eu.senla.gatewayservice.exception;

import eu.senla.gatewayservice.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends ApiException {
    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode.getMessage(), HttpStatus.UNAUTHORIZED, errorCode);
    }
}
