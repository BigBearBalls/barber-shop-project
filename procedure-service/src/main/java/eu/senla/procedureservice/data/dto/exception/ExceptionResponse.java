package eu.senla.procedureservice.data.dto.exception;

import eu.senla.procedureservice.enums.ErrorCode;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp, ErrorCode code, String message, String path) {
}
