package eu.senla.catalog.util.enums;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionInfo {
    NOT_FOUND_ID("not.found.id.message", 40401),
    NAME_EXIST("name.exist.message", 40902);

    private final String exceptionMessage;
    private final int exceptionCode;

    public static int getExceptionCodeByMessage(String message) {
        return Arrays
                .stream(ExceptionInfo.values())
                .filter(exceptionInfo -> exceptionInfo.exceptionMessage.equals(message))
                .map(exceptionInfo -> exceptionInfo.exceptionCode)
                .findFirst()
                .orElse(0);
    }
}
