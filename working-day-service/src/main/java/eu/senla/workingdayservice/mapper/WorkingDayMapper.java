package eu.senla.workingdayservice.mapper;

import eu.senla.common.workingday.dto.request.RequestWorkingDayDTO;
import eu.senla.common.workingday.dto.response.ResponseWorkingDayDTO;
import eu.senla.workingdayservice.entity.WorkingDay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WorkingDayMapper {

    @Mapping(target = "id", ignore = true)
    WorkingDay toWorkingDay(RequestWorkingDayDTO requestWorkingDayDto);

    ResponseWorkingDayDTO toWorkingDayDto(WorkingDay workingDay);
}
