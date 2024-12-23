package eu.senla.httpconfiguration.exceptioncontroller.exception;

import eu.senla.common.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExternalApiException extends ApiException {

    private HttpStatus httpStatus;

    public ExternalApiException(String message) {
        super(message, ErrorCode.ERR_EXTERNAL_API);
        this.httpStatus = HttpStatus.I_AM_A_TEAPOT;
    }

    public ExternalApiException() {
        super(ErrorCode.ERR_EXTERNAL_API.getMessage(), ErrorCode.ERR_EXTERNAL_API);
        this.httpStatus = HttpStatus.I_AM_A_TEAPOT;
    }

    public ExternalApiException(String message, ErrorCode errorCode) {
        super(message, errorCode);
        this.httpStatus = HttpStatus.I_AM_A_TEAPOT;
    }

    public ExternalApiException(String message, ErrorCode errorCode, HttpStatus httpStatus) {
        super(message, errorCode);
        this.httpStatus = httpStatus;
    }
}
