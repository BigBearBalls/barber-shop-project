package eu.senla.catalog.service.impl;

import eu.senla.catalog.entity.Master;
import eu.senla.catalog.entity.Procedure;
import eu.senla.catalog.exception.MasterNotFoundByIdException;
import eu.senla.catalog.repository.MasterRepository;
import eu.senla.catalog.service.MasterService;
import eu.senla.catalog.util.enums.ExceptionInfo;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService {

    private final MasterRepository masterRepository;

    @Override
    public List<Procedure> findProcedures(UUID masterId) {
        Optional<Master> master = masterRepository.findById(masterId);
        if(master.isEmpty()) {
            throw new MasterNotFoundByIdException(masterId, ExceptionInfo.NOT_FOUND_ID);
        }
        return master.get().getProcedures();
    }
}
