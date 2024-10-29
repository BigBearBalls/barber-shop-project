package eu.senla.booking.service;

import eu.senla.booking.dto.GetMasterFreeTimesResponse;
import eu.senla.booking.entity.MasterTimeTable;

import java.util.UUID;

public interface MasterTimeTableService {

    GetMasterFreeTimesResponse getMasterFreeTimes(UUID masterId);

    MasterTimeTable getMasterTimeTable(Long masterTimeTableId);
}
