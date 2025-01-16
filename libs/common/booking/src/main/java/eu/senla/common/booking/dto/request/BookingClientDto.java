package eu.senla.common.booking.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookingClientDto {

    private String clientName;
    private String clientLastName;
    private String phoneNumber;

}
