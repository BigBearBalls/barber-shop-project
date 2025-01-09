package eu.senla.httpconfiguration.exceptioncontroller.dto;

import eu.senla.httpconfiguration.exceptioncontroller.enums.ErrorCode;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp, ErrorCode code, String message, String path) {
}
