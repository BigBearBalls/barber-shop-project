package eu.senla.procedureservice.service.mapper;

import eu.senla.procedureservice.data.dto.request.ProcedureDTO;
import eu.senla.procedureservice.data.entity.Procedure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProcedureMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "procedureName", source = "procedureName")
    @Mapping(target = "procedurePrice", source = "procedurePrice")
    @Mapping(target = "procedureDuration", source = "procedureDuration")
    Procedure toProcedure(ProcedureDTO procedureDto);
}
