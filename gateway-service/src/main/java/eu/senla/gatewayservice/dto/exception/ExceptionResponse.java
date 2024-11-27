package eu.senla.gatewayservice.dto.exception;

import eu.senla.gatewayservice.enums.ErrorCode;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp, ErrorCode code, String message, String path) {
}
