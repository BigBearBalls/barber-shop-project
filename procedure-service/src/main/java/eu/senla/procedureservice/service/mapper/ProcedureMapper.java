package eu.senla.procedureservice.service.mapper;

import eu.senla.common.dto.ProcedureDTO;
import eu.senla.common.procedure.dto.request.CreateProcedureRequest;
import eu.senla.procedureservice.data.entity.Procedure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProcedureMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "procedureName", source = "procedureName")
    @Mapping(target = "procedurePrice", source = "procedurePrice")
    @Mapping(target = "procedureDuration", source = "procedureDuration")
    Procedure toProcedure(CreateProcedureRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "procedureName", source = "procedureName")
    @Mapping(target = "procedurePrice", source = "procedurePrice")
    @Mapping(target = "duration", source = "procedureDuration")
    ProcedureDTO toProcedureDTO(Procedure procedure);

    default List<ProcedureDTO> toListDTO(List<Procedure> procedures) {
        return procedures.stream().map(this::toProcedureDTO).toList();
    }
}
