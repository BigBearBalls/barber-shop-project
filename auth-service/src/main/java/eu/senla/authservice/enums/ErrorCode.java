package eu.senla.authservice.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

    ERR_OBJECT_NOT_FOUND("The requested object with ID %s was not found!"),
    ERR_USER_NOT_FOUND("User with login '%s' was not found!"),
    ERR_WRONG_CREDENTIALS("Wrong user credentials!"),
    ERR_UNKNOWN_CODE("Something went wrong!"),
    ERR_JWT_VALIDATION_EXCEPTION("Something went wrong during jwt validation!"),;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
