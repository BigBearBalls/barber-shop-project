package eu.senla.httpconfiguration.exceptioncontroller.util;

import eu.senla.httpconfiguration.exceptioncontroller.enums.ErrorCode;
import eu.senla.httpconfiguration.exceptioncontroller.dto.ExceptionResponse;
import eu.senla.httpconfiguration.exceptioncontroller.exception.ApiException;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class ExceptionWrapper {

    public static ExceptionResponse buildExceptionResponse(ApiException e, String uri) {
        return buildExceptionResponse(e.getErrorCode(), e.getMessage(), uri);
    }

    public static ExceptionResponse buildExceptionResponse(ErrorCode errorCode, String message, String uri) {
        return new ExceptionResponse(LocalDateTime.now(), errorCode, message, uri);
    }
}
