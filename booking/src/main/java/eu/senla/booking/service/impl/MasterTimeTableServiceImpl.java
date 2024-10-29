package eu.senla.booking.service.impl;

import eu.senla.booking.dto.GetMasterFreeTimesResponse;
import eu.senla.booking.entity.MasterTimeTable;
import eu.senla.booking.enums.ErrorCode;
import eu.senla.booking.exception.LogExceptionWrapper;
import eu.senla.booking.exception.MasterTimeTableNotFoundException;
import eu.senla.booking.mapper.MasterTimeTableMapper;
import eu.senla.booking.repository.MasterTimeTableRepository;
import eu.senla.booking.service.MasterTimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MasterTimeTableServiceImpl implements MasterTimeTableService {

    private final MasterTimeTableRepository masterTimeTableRepository;

    private final MasterTimeTableMapper masterTimeTableMapper;

    @Override
    public MasterTimeTable getMasterTimeTable(Long masterTimeTableId) {
        return masterTimeTableRepository.findById(masterTimeTableId).orElseThrow(() -> LogExceptionWrapper.logErrorException(
                        new MasterTimeTableNotFoundException(masterTimeTableId.toString(), ErrorCode.ERR_OBJECT_NOT_FOUND)));
    }

    @Override
    public GetMasterFreeTimesResponse getMasterFreeTimes(UUID masterId) {
        List<MasterTimeTable> list = masterTimeTableRepository.findAllByMasterIdAndIsAvailable(masterId, true);
        return masterTimeTableMapper.toResponse(list);
    }
}
