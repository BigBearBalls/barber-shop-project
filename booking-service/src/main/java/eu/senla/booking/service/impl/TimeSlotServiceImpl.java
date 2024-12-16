package eu.senla.booking.service.impl;

import eu.senla.booking.entity.TimeSlot;
import eu.senla.booking.repository.TimeSlotRepository;
import eu.senla.booking.service.TimeSlotService;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    @Override
    public List<TimeSlot> findTimeSlotsForBooking(LocalTime reservationStart,LocalTime reservationEnd) {

        return timeSlotRepository.findAllByReservationStartGreaterThanEqualAndReservationEndLessThanEqual(reservationStart,
                                                                                                          reservationEnd);
    }
}
