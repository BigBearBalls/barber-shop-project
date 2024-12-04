package eu.senla.common.workingday.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarFeignData {

    private Boolean isHoliday;
    private RequestWorkingDayDTO requestWorkingDayDto;

}
