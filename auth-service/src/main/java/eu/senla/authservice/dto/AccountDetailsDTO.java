package eu.senla.authservice.dto;

import lombok.Data;

@Data
public class AccountDetailsDTO {

    private String id;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;
}
