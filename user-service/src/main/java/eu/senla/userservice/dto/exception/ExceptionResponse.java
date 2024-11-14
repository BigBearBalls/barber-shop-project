package eu.senla.userservice.dto.exception;

import eu.senla.userservice.enums.ErrorCode;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp, ErrorCode code, String message, String path) {
}
