package eu.senla.workingdayservice.exception;

import lombok.Getter;

@Getter
public abstract class AbstractException extends RuntimeException {

    private final String code;
    private final String message;

    public AbstractException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
