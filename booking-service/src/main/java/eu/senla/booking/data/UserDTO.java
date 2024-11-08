package eu.senla.booking.data;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDTO {
    private UUID id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
}
