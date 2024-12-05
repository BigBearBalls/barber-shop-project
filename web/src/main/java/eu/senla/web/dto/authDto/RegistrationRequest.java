package eu.senla.web.dto.authDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

}
