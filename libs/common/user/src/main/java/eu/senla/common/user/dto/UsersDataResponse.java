package eu.senla.common.user.dto;

import eu.senla.common.dto.UserDataDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersDataResponse {
    List<UserDataDTO> users;
}
