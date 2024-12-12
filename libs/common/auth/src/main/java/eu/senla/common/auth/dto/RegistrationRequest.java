package eu.senla.common.auth.dto;

import eu.senla.common.constant.ValidationConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationRequest {

    @NotBlank(message = ValidationConstants.FIRST_NAME_CANNOT_BE_BLANK_VALIDATION_MESSAGE)
    @Pattern(regexp = ValidationConstants.NAME_REGEXP_PATTERN,
            message = ValidationConstants.FIRST_NAME_IS_NOT_VALID_VALIDATION_MESSAGE)
    private String firstName;

    @NotBlank(message = ValidationConstants.LAST_NAME_CANNOT_BE_BLANK_VALIDATION_MESSAGE)
    @Pattern(regexp = ValidationConstants.SURNAME_REGEXP_PATTERN,
            message = ValidationConstants.LAST_NAME_IS_NOT_VALID_VALIDATION_MESSAGE)
    private String lastName;

    @Email(message = ValidationConstants.EMAIL_ADDRESS_IS_NOT_VALID_VALIDATION_MESSAGE)
    @NotBlank(message = ValidationConstants.EMAIL_ADDRESS_CANNOT_BE_BLANK_VALIDATION_MESSAGE)
    private String email;

    @NotBlank(message = ValidationConstants.PASSWORD_CANNOT_BE_BLANK_VALIDATION_MESSAGE)
    @Size(min = ValidationConstants.MIN_PASSWORD_LENGTH, max = ValidationConstants.MAX_PASSWORD_LENGTH,
            message = ValidationConstants.PASSWORD_MUST_BE_BETWEEN_VALIDATION_MESSAGE)
    private String password;

    @NotBlank(message = ValidationConstants.PHONE_NUMBER_CANNOT_BE_BLANK_VALIDATION_MESSAGE)
    @Pattern(regexp = ValidationConstants.BY_PHONE_REGEXP_PATTERN,
            message = ValidationConstants.PHONE_NUMBER_IS_NOT_VALID_VALIDATION_MESSAGE)
    private String phoneNumber;
}
