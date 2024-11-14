package eu.senla.workingdayservice.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionInfo {

    WORKING_DAY_NOT_FOUND_BY_ID("not.found.id.message", "40401"),
    WORKING_DAY_NOT_FOUND_BY_DATE("not.found.id.message", "40402"),
    ENTITY_EXIST("nickname.exist.message", "40901"),
    WORKING_DAY_NOT_FOUND_BY_ID_DATE("This master doesnt work at date you are looking for", "40403");

    private final String exceptionMessage;
    private final String exceptionCode;
}
