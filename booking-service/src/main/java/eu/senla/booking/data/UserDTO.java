package eu.senla.booking.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class UserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
