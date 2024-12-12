package eu.senla.common.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

    ERR_OBJECT_NOT_FOUND("The requested object with ID %s was not found!"),
    ERR_WRONG_CREDENTIALS("Wrong user credentials!"),
    ERR_UNKNOWN_CODE("Something went wrong!"),
    ERR_USER_NOT_FOUND("User with %s '%s' was not found!"),
    ERR_USER_ALREADY_EXISTS("User with %s '%s' already exists!"),
    ERR_JWT_VALIDATION_EXCEPTION("Something went wrong during jwt validation!"),
    ERR_HEADER_NOT_EXIST_OR_WRONG_VALUE("Header not exists or value is wrong!"),
    ERR_METHOD_ARGUMENTS_VALIDATION_EXCEPTION("Method arguments validation failed!"),
    ERR_ACCESS_DENIED("Access Denied!"),
    ERR_JSON_PARSE_EXCEPTION("JSON parse exception!"),
    ERR_DONT_AUTHENTICATED("Don't authenticated!"),
    ERR_MISSING_HEADER("Missing header!"),
    ERR_HTTP_METHOD_NOT_ALLOWED("HTTP Method not allowed!"),
    ERR_NOT_DAY_OFF("This day (%s) is not day off!"),
    ERR_ALREADY_DAY_OFF("This day (%s) is already day off!"),
    ERR_DATE_CANNOT_BE_IN_PAST("Date cannot be in the past!"),
    ERR_MASTER_NOT_WORKING("Master is not working!"),
    ERR_TIME_ALREADY_BOOKED("Time already booked!"),
    ERR_BOOKING_NOT_FOUND("Booking with ID %s was not found!"),
    ERR_MASTER_DOESNT_PROVIDE_THIS_PROCEDURE("Master does not provide this procedure!"),
    ERR_PROCEDURE_NOT_FOUND("Procedure with %s '%s' was not found!"),
    ERR_PROCEDURE_ALREADY_EXISTS("Procedure with %s '%s' already exists!"),
    ERR_VALIDATION("Validation failed!"),
    ERR_VALIDATION_NOT_NULL_PROCEDURE("Procedure name cannot be null"),
    ERR_MASTER_PROCEDURE_EXIST("This master is already assigned to this procedure."),
    ERR_WORKING_DAY_NOT_FOUND("Working day couldn't be found by %s '%s'"),
    ERR_ENTITY_EXIST("Can't be saved. Entity already exist"),
    ERR_WORKING_DAY_NOT_FOUND_BY_ID_DATE("This master doesnt work at %s you are looking for"),
    ERR_WORKING_DAY_ALREADY_EXIST("This master is already working on this day."),
    ERR_HANDLER_EXCEPTION("Application exception"),
    ERR_VALIDATION_ERROR("Validation error"),
    ERR_DAY_IS_HOLIDAY("This Date is not working"),
    ERR_ERR_CONSTRAINT_VIOLATION_EXCEPTION("Constraint violation!");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
