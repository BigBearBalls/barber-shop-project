package eu.senla.booking.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationConstants {
    public static final int MIN_ID_VALUE_VALIDATION = 1;
    public static final String BOOKING_ID_CANNOT_BE_LESS_THAN_VALIDATION_MESSAGE = "Booking id cannot be less than 1";
    public static final String USER_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE = "User ID cannot be null!";
    public static final String PROCEDURE_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE = "User ID cannot be null!";
    public static final String PROCEDURE_ID_CANNOT_BE_LESS_THAN_VALIDATION_MESSAGE = "User ID cannot be less than 1!";
    public static final String MASTER_TIMETABLE_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE = "Timetable ID cannot be null!";
    public static final String MASTER_TIMETABLE_ID_CANNOT_BE_LESS_THAN_VALIDATION_MESSAGE = "Timetable ID cannot be less than 1!";
}
