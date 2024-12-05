package eu.senla.web.dto.authDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddWorkingDayRequest {

    private String masterId;
    private String workingDate;
    private String workStart;
    private String workEnd;

}
