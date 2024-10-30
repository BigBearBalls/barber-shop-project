package eu.senla.booking.service;

import eu.senla.booking.dto.DateTimeRequest;
import eu.senla.booking.dto.GetMasterFreeTimesResponse;
import eu.senla.booking.entity.MasterTimetable;

import java.util.UUID;

public interface MasterTimetableService {

    GetMasterFreeTimesResponse getMasterFreeTimes(UUID masterId);

    MasterTimetable getMasterTimeTable(Long masterTimeTableId);

    void markDayAsWorkDay(UUID masterId, DateTimeRequest request);
}
