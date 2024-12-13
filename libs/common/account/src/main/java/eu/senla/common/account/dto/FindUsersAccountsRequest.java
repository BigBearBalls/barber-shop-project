package eu.senla.common.account.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FindUsersAccountsRequest {
    private String firstName;
    private String lastName;
}
