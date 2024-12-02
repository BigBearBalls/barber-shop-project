package eu.senla.httpconfiguration.dto;

import eu.senla.httpconfiguration.enums.ErrorCode;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp, ErrorCode code, String message, String path) {
}
