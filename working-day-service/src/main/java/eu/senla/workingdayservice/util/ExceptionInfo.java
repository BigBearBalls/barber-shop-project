package eu.senla.workingdayservice.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionInfo {

    WORKING_DAY_NOT_FOUND_BY_ID("Working day couldn't be found by date", "40401"),
    WORKING_DAY_NOT_FOUND_BY_DATE("Working day couldn't be found by date", "40402"),
    ENTITY_EXIST("Can't be saved. Entity already exist", "40901"),
    WORKING_DAY_NOT_FOUND_BY_ID_DATE("This master doesnt work at date you are looking for", "40403"),
    WORKING_DAY_ALREADY_EXIST("This master is already working on this day.", "40404"),
    HANDLER_EXCEPTION ("Application exception", "409000"),
    VALIDATION_ERROR ("Validation error", "400001"),
    DAY_IS_HOLIDAY ("This Date is not working", "400002");
    private final String exceptionMessage;
    private final String exceptionCode;
}
