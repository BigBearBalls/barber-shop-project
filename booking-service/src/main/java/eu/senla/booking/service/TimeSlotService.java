package eu.senla.booking.service;

import eu.senla.booking.entity.TimeSlot;
import java.time.LocalTime;
import java.util.List;

public interface TimeSlotService {

    List<TimeSlot> findTimeSlotsForBooking(LocalTime reservationStart, LocalTime reservationEnd);
}
