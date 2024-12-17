package eu.senla.common.department.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShortDepartmentUserInfoResponse {

    Set<ShortDepartmentUserInfoDTO> users;
}
