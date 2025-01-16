package eu.senla.common.booking.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingOwner {

    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}
