package eu.senla.common.exception;

import eu.senla.common.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class MasterNotWorkException extends ApiException {
    public MasterNotWorkException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.BAD_REQUEST, errorCode);
    }

    public MasterNotWorkException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
}
