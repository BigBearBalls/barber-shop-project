package eu.senla.catalog.mapper;

import eu.senla.catalog.dto.ProcedureDto;
import eu.senla.catalog.entity.Procedure;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProcedureMapper {

    Procedure toProcedure(ProcedureDto procedureDto);

    ProcedureDto toProcedureDto(Procedure procedure);
}
