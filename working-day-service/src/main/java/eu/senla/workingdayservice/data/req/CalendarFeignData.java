package eu.senla.workingdayservice.data.req;

import eu.senla.workingdayservice.dto.RequestWorkingDayDto;
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
    private RequestWorkingDayDto requestWorkingDayDto;

}
