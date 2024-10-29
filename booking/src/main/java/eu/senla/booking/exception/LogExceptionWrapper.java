package eu.senla.booking.exception;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class LogExceptionWrapper {

    public static <T extends Exception> T logErrorException(T exception) {
        log.error(exception.getMessage(), exception);
        return exception;
    }
}

