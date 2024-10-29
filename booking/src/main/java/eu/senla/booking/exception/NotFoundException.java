package eu.senla.booking.exception;

import eu.senla.booking.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

    public NotFoundException(String id, ErrorCode errorStatus) {
        super(String.format(ErrorCode.ERR_OBJECT_NOT_FOUND.getMessage(), id), HttpStatus.NOT_FOUND, errorStatus);
    }

}
