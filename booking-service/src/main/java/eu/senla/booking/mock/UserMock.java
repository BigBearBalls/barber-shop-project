package eu.senla.booking.mock;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserMock {
    private UUID id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
}
