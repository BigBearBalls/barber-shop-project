package eu.senla.booking.exception;

import eu.senla.booking.enums.ErrorCode;

public class BookingNotFoundException extends NotFoundException {
    public BookingNotFoundException(String message, ErrorCode errorCode) {
        super(errorCode.getMessage() + message, errorCode);
    }
}
