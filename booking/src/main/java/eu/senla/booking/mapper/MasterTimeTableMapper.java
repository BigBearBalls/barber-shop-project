package eu.senla.booking.mapper;

import eu.senla.booking.dto.GetMasterFreeTimesResponse;
import eu.senla.booking.dto.MasterTimeTableDTO;
import eu.senla.booking.entity.MasterTimeTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MasterTimeTableMapper {

    @Mapping(target = "masterFreeTimeId", source = "id")
    @Mapping(target = "time", source = "dayTime.time")
    @Mapping(target = "date", source = "date")
    MasterTimeTableDTO toDTO(MasterTimeTable masterTimeTable);

    default GetMasterFreeTimesResponse toResponse(List<MasterTimeTable> list) {
        List<MasterTimeTableDTO> out = list.stream().map(this::toDTO).toList();
        return new GetMasterFreeTimesResponse(out);
    }
}
