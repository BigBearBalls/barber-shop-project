package eu.senla.common.user.dto;

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
