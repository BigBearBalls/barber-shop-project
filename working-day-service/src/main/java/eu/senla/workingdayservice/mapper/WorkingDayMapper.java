package eu.senla.workingdayservice.mapper;

import eu.senla.workingdayservice.dto.WorkingDayDto;
import eu.senla.workingdayservice.entity.WorkingDay;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkingDayMapper {

    WorkingDay toWorkingDay(WorkingDayDto workingDayDto);

    WorkingDayDto toWorkingDayDto(WorkingDay workingDay);
}
