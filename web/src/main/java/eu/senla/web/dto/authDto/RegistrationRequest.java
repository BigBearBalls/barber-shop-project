package eu.senla.web.dto.authDto;

import lombok.*;

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
