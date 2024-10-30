package eu.senla.booking.dto.exception;

import java.time.LocalDateTime;

public record ExceptionResponse(String errorMessage, int errorCode, LocalDateTime timestamp, String path) {
}
