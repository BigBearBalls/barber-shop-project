package eu.senla.userservice.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

    ERR_OBJECT_NOT_FOUND("The requested object with ID %s was not found!"),
    ERR_USER_NOT_FOUND("User with %s '%s' was not found!"),
    ERR_USER_ALREADY_EXISTS("User with %s '%s' already exists!"),
    ERR_WRONG_CREDENTIALS("Wrong user credentials!"),
    ERR_UNKNOWN_CODE("Something went wrong!"),
    ERR_JWT_VALIDATION_EXCEPTION("Something went wrong during jwt validation!"),
    ERR_CONSTRAINT_VIOLATION_EXCEPTION("Constraint violation!"),
    ERR_METHOD_ARGUMENTS_VALIDATION_EXCEPTION("Method arguments validation failed!"),
    ERR_HEADER_NOT_EXIST_OR_WRONG_VALUE("Header not exists or value is wrong!"),
    ERR_ACCESS_DENIED("Access Denied!");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
