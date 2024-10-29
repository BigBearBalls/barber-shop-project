package eu.senla.booking.exception;

import eu.senla.booking.enums.ErrorCode;

public class MasterTimeTableNotFoundException extends NotFoundException {
    public MasterTimeTableNotFoundException(String id, ErrorCode errorCode) {
        super(id, errorCode);
    }
}
