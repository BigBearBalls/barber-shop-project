package eu.senla.booking.exception;

import eu.senla.booking.enums.ErrorCode;

public class DayTimeNotFoundException extends NotFoundException {
    public DayTimeNotFoundException(String message, ErrorCode errorCode) {
        super(errorCode.getMessage() + message, errorCode);
    }
}
