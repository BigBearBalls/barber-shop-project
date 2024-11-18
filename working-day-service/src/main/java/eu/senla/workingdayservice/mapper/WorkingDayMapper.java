package eu.senla.workingdayservice.mapper;

import eu.senla.workingdayservice.dto.WorkingDayDto;
import eu.senla.workingdayservice.entity.WorkingDay;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WorkingDayMapper {

    WorkingDay toWorkingDay(WorkingDayDto workingDayDto);

    WorkingDayDto toWorkingDayDto(WorkingDay workingDay);
}
