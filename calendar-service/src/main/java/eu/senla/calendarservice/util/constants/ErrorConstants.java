package eu.senla.calendarservice.util.constants;

public class ErrorConstants {

    public static final String MISMATCH_EXCEPTION_ERROR_CODE = "400000";
    public static final String MISMATCH_EXCEPTION_ERROR_MESSAGE = "Use correct YYYY-MM-DD date format";
    public static final String INVALID_DATE_ERROR_CODE = "400001";
    public static final String INVALID_DATE_ERROR_MESSAGE = "Can't use a past date ";
    public static final String DATE_IS_ALREADY_EXIST_ERROR_CODE = "400002";
    public static final String DATE_IS_ALREADY_EXIST_ERROR_MESSAGE = "This date is already day-off";
    public static final String EMPTY_DATE_ERROR_CODE = "400003";
    public static final String EMPTY_DATE_ERROR_MESSAGE = "This day is already a working day";
    public static final String EXCEPTION_ERROR_CODE = "500009";
    public static final String EXCEPTION_ERROR_MESSAGE = "Application exception";
    public static final String HANDLE_FEIGN_CLIENT_EXCEPTION = "400004";
    public static final String HANDLE_FEIGN_CLIENT_EXCEPTION_MESSAGE = "Feign client exception!";

}
