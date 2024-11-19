package eu.senla.authservice.dto;

import eu.senla.authservice.constant.ValidationConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @Email(message = ValidationConstants.EMAIL_ADDRESS_IS_NOT_VALID_VALIDATION_MESSAGE)
    @NotBlank(message = ValidationConstants.EMAIL_ADDRESS_CANNOT_BE_BLANK_VALIDATION_MESSAGE)
    private String email;

    @NotBlank(message = ValidationConstants.PASSWORD_CANNOT_BE_BLANK_VALIDATION_MESSAGE)
    @Size(min = ValidationConstants.MIN_PASSWORD_LENGTH, max = ValidationConstants.MAX_PASSWORD_LENGTH,
            message = ValidationConstants.PASSWORD_MUST_BE_BETWEEN_VALIDATION_MESSAGE)
    private String password;
}
