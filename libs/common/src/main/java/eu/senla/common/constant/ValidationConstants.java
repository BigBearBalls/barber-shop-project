package eu.senla.common.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationConstants {

    public static final String NAME_REGEXP_PATTERN = "^[a-zA-Zа-яА-ЯёЁ'-]{2,50}$";
    public static final String SURNAME_REGEXP_PATTERN = "^[a-zA-Zа-яА-ЯёЁ'-]{2,50}$";
    public static final String UNIVERSAL_PHONE_REGEXP_PATTERN
            = "^[\\+]?[0-9]{1,3}[-\\s\\.]?[(]?[0-9]{1,3}[)]?[-\\s\\.]?[0-9]{4,7}$";
    public static final String BY_PHONE_REGEXP_PATTERN = "^\\+375[-\\s\\.]?[(]?(25|29|33|44)[)]?[-\\s\\.]?[0-9]{7}$";
    public static final String EMAIL_ADDRESS_IS_NOT_VALID_VALIDATION_MESSAGE = "Email address is not valid!";
    public static final String EMAIL_ADDRESS_CANNOT_BE_BLANK_VALIDATION_MESSAGE = "Email address cannot be blank!";
    public static final String PASSWORD_CANNOT_BE_BLANK_VALIDATION_MESSAGE = "Password cannot be blank!";
    public static final String PASSWORD_MUST_BE_BETWEEN_VALIDATION_MESSAGE = "Password must be between 6 and 50 chars!";
    public static final int MAX_PASSWORD_LENGTH = 50;
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final String PHONE_NUMBER_CANNOT_BE_BLANK_VALIDATION_MESSAGE = "Phone number cannot be blank!";
    public static final String PHONE_NUMBER_IS_NOT_VALID_VALIDATION_MESSAGE = "Phone number is not valid!";
    public static final String LAST_NAME_CANNOT_BE_BLANK_VALIDATION_MESSAGE = "Last name cannot be blank!";
    public static final String FIRST_NAME_CANNOT_BE_BLANK_VALIDATION_MESSAGE = "First name cannot be blank!";
    public static final String FIRST_NAME_IS_NOT_VALID_VALIDATION_MESSAGE = "First name is not valid!";
    public static final String LAST_NAME_IS_NOT_VALID_VALIDATION_MESSAGE = "Last name is not valid!";
    public static final String USER_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE = "User ID cannot be null!";
    public static final int MIN_PERMISSIONS_LIST_SIZE = 1;
    public static final String PROCEDURE_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE = "Procedure ID cannot be null!";
    public static final String MASTER_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE = "Master ID cannot be null!";
    public static final String CLIENT_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE = "Client ID cannot be null!";
    public static final String RESERVATION_START_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE = "Reservation start time cannot be null!";
    public static final String WORKING_DATE_CANNOT_BE_NULL_VALIDATION_MESSAGE = "Working date cannot be null!";
    public static final String BOOKING_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE = "Booking ID cannot be null!";
    public static final String MEETING_ROOM_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE = "Booking ID cannot be null!";
    public static final String SIZE_OF_PERMISSIONS_LIST_CANNOT_BE_LESS_THEN_VALIDATION_MESSAGE = "Size of permissions list cannot be less then 1!";
    public static final String WORKING_DAY_START_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE = "Working day start time cannot be null!";
    public static final String WORKING_DAY_END_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE = "Working day end time cannot be null!";
    public static final String WORKING_MASTER_CANNOT_BE_NULL_VALIDATION_MESSAGE = "Master id cannot be null!";
    public static final String BOOKING_START_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE = "Booking start time cannot be null!";
    public static final String BOOKING_END_TIME_CANNOT_BE_NULL_VALIDATION_MESSAGE = "Booking end time cannot be null!";
    public static final String BOOKING_DATE_CANNOT_BE_NULL_VALIDATION_MESSAGE = "Booking date cannot be null!";
    public static final String TEAM_LEADER_ID_CANNOT_BE_NULL_VALIDATION_MESSAGE = "Team leader id cannot be null!";
    public static final String DEPARTMENT_NAME_CANNOT_BE_NULL_OR_BLANK_VALIDATION_MESSAGE = "Department name cannot be null or blank!";
    public static final String REGISTRATION_TOKEN_CANNOT_BE_BLANK_VALIDATION_MESSAGE = "Registration token cannot be null or blank!";
    public static final String DEPARTMENT_NAME_LENGTH_MUST_BE_BETWEEN_VALIDATION_MESSAGE = "Department name length must be between 5 and 20 chars!";
    public static final int MAX_LENGTH_OF_DEPARTMENT_NAME = 20;
    public static final int MIN_LENGTH_OF_DEPARTMENT_NAME = 5;
}
