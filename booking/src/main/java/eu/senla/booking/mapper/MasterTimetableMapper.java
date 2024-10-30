package eu.senla.booking.mapper;

import eu.senla.booking.dto.DateTimeRequest;
import eu.senla.booking.dto.GetMasterFreeTimesResponse;
import eu.senla.booking.dto.MasterTimetableDTO;
import eu.senla.booking.entity.MasterTimetable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MasterTimetableMapper {

    @Mapping(target = "masterTimetableId", source = "id")
    @Mapping(target = "dayTime", source = "dayTime")
    @Mapping(target = "date", source = "date")
    MasterTimetableDTO toDTO(MasterTimetable masterTimetable);

    default GetMasterFreeTimesResponse toResponse(List<MasterTimetable> list) {
        List<MasterTimetableDTO> out = list.stream().map(this::toDTO).toList();
        return new GetMasterFreeTimesResponse(out);
    }

    @Mapping(target = "masterId", source = "masterId")
    @Mapping(target = "date", source = "request.date")
    @Mapping(target = "dayTime", ignore = true)
    @Mapping(target = "isAvailable", constant = "true")
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    MasterTimetable toEntity(UUID masterId, DateTimeRequest request);
}
