package eu.senla.httpconfiguration.core.util;

import eu.senla.common.dto.exception.ExceptionResponse;
import eu.senla.common.enums.ErrorCode;
import eu.senla.common.exception.ApiException;
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
