package eu.senla.booking.mapper;

import eu.senla.booking.dto.DayTimeDTO;
import eu.senla.booking.dto.DayTimesResponse;
import eu.senla.booking.entity.DayTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DayTimeMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "time", source = "time")
    DayTimeDTO toDTO(DayTime dayTime);

    default DayTimesResponse toResponse(List<DayTime> list) {
        List<DayTimeDTO> out = list.stream().map(this::toDTO).toList();
        return new DayTimesResponse(out);
    }
}
