package eu.senla.web.dto.authDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    private String id;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

}
