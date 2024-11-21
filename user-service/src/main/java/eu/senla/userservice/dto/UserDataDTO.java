package eu.senla.userservice.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserDataDTO {

    private UUID id;

    private String firstName;

    private String lastName;

    private String phoneNumber;
}
