package eu.senla.common.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PreviewsAccountsResponse {

    public List<PreviewAccountDTO> accounts;
}
