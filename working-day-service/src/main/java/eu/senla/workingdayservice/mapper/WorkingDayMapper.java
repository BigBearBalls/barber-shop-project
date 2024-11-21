package eu.senla.workingdayservice.mapper;

import eu.senla.workingdayservice.dto.RequestWorkingDayDto;
import eu.senla.workingdayservice.dto.ResponseWorkingDayDto;
import eu.senla.workingdayservice.entity.WorkingDay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WorkingDayMapper {

    @Mapping(target = "id", ignore = true)
    WorkingDay toWorkingDay(RequestWorkingDayDto requestWorkingDayDto);


    ResponseWorkingDayDto toWorkingDayDto(WorkingDay workingDay);
}
