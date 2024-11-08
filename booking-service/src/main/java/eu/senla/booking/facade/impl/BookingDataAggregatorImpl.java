package eu.senla.booking.facade.impl;

import eu.senla.booking.client.ProcedureClient;
import eu.senla.booking.client.UserClient;
import eu.senla.booking.data.ProcedureDTO;
import eu.senla.booking.data.UserDTO;
import eu.senla.booking.data.mapper.BookingResponseMapper;
import eu.senla.booking.data.request.AggregatedBooking;
import eu.senla.booking.data.request.BookingRequestDTO;
import eu.senla.booking.data.response.BookingResponseDTO;
import eu.senla.booking.entity.Booking;
import eu.senla.booking.entity.WorkingDay;
import eu.senla.booking.facade.BookingDataAggregator;
import eu.senla.booking.service.WorkingDayService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingDataAggregatorImpl implements BookingDataAggregator {

    private final WorkingDayService workingDayService;
    private final ProcedureClient procedureClient;
    private final UserClient userClient;
    private final BookingResponseMapper bookingMapper;


    @Override
    public AggregatedBooking collectDataForSaving(BookingRequestDTO bookingRequestDto) {
        WorkingDay workingMasterDay = workingDayService
                .findWorkingDayByMasterAndWorkingDate(bookingRequestDto.getMasterId(),
                        bookingRequestDto.getWorkingDate());

        ProcedureDTO procedure = procedureClient
                .findProcedureByIdAndMasterId(bookingRequestDto.getProcedureId(),
                        bookingRequestDto.getMasterId());

        return new AggregatedBooking(workingMasterDay, procedure);
    }

    @Override
    public BookingResponseDTO collectResponseData(Booking booking) {

        WorkingDay workingDay = workingDayService.findById(booking.getWorkingDayId());
        ProcedureDTO procedure = procedureClient.findProcedureByIdAndMasterId(booking.getProcedureId(),
                workingDay.getMaster());

        UserDTO client = userClient.findUserById(booking.getClientId());
        UserDTO master = userClient.findUserById(workingDay.getMaster());

        return bookingMapper.toBookingResponseDTO(client, master, procedure, booking, workingDay);
    }


}
