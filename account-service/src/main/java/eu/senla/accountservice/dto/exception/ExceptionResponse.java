package eu.senla.accountservice.dto.exception;

import eu.senla.accountservice.enums.ErrorCode;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp, ErrorCode code, String message, String path) {
}
