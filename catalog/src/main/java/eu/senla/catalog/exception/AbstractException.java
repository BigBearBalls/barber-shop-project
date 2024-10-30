package eu.senla.catalog.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public abstract class AbstractException extends RuntimeException{

    private final int exceptionCode;
    private final String exceptionMessage;
    private final HttpStatus httpStatus;
}
