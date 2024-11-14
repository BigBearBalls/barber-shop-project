package eu.senla.authservice.dto.exception;

import eu.senla.authservice.enums.ErrorCode;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp, ErrorCode code, String message, String path) {
}
