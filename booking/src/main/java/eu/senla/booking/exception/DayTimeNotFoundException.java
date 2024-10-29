package eu.senla.booking.exception;

import eu.senla.booking.enums.ErrorCode;

public class DayTimeNotFoundException extends NotFoundException {
    public DayTimeNotFoundException(String id, ErrorCode errorCode) {
        super(id, errorCode);
    }
}
