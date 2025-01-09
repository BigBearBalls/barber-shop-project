package eu.senla.httpconfiguration.exceptioncontroller.exception;

import eu.senla.httpconfiguration.exceptioncontroller.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExternalApiException extends ApiException {

    public ExternalApiException(String message) {
        super(message, HttpStatus.I_AM_A_TEAPOT, ErrorCode.ERR_EXTERNAL_API);
    }

    public ExternalApiException() {
        super(ErrorCode.ERR_EXTERNAL_API.getMessage(), HttpStatus.I_AM_A_TEAPOT, ErrorCode.ERR_EXTERNAL_API);
    }

    public ExternalApiException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.I_AM_A_TEAPOT, errorCode);
    }

    public ExternalApiException(String message, ErrorCode errorCode, HttpStatus httpStatus) {
        super(message, httpStatus, errorCode);
    }
}
