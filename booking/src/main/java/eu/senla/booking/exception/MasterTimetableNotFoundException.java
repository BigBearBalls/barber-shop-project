package eu.senla.booking.exception;

import eu.senla.booking.enums.ErrorCode;

public class MasterTimetableNotFoundException extends NotFoundException {
    public MasterTimetableNotFoundException(String message, ErrorCode errorCode) {
        super(errorCode.getMessage() + message, errorCode);
    }
}
