package eu.senla.catalog.service.impl;

import eu.senla.catalog.dto.ProcedureDto;
import eu.senla.catalog.entity.Master;
import eu.senla.catalog.exception.NotFoundByIdException;
import eu.senla.catalog.mapper.ProcedureMapper;
import eu.senla.catalog.repository.MasterRepository;
import eu.senla.catalog.service.MasterService;
import eu.senla.catalog.util.enums.ExceptionInfo;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService {

    private final MasterRepository masterRepository;
    private final ProcedureMapper procedureMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProcedureDto> getProcedures(UUID masterId) {
        Master master = masterRepository
                .findById(masterId)
                .orElseThrow(() -> new NotFoundByIdException(String.valueOf(masterId), ExceptionInfo.NOT_FOUND_ID));
        return master
                .getProcedures()
                .stream()
                .map(procedureMapper::toProcedureDto)
                .toList();
    }
}
