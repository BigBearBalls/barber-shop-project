package eu.senla.booking.facade.impl;

import eu.senla.booking.client.ProcedureClient;
import eu.senla.booking.client.UserClient;
import eu.senla.booking.client.WorkingDayClient;
import eu.senla.booking.data.ProcedureDTO;
import eu.senla.booking.data.UserDTO;
import eu.senla.booking.data.WorkingDayDto;
import eu.senla.booking.data.mapper.BookingResponseMapper;
import eu.senla.booking.data.request.AggregatedBooking;
import eu.senla.booking.data.request.BookingRequestDTO;
import eu.senla.booking.data.response.BookingResponseDTO;
import eu.senla.booking.entity.Booking;
import eu.senla.booking.facade.BookingDataAggregator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingDataAggregatorImpl implements BookingDataAggregator {

    private final WorkingDayClient workingDayClient;
    private final ProcedureClient procedureClient;
    private final UserClient userClient;
    private final BookingResponseMapper bookingMapper;


    @Override
    public AggregatedBooking collectDataForSaving(BookingRequestDTO bookingRequestDto) {
        WorkingDayDto workingMasterDay = workingDayClient.findByMasterIdAndWorkingDate(bookingRequestDto.getMasterId(),
                bookingRequestDto.getWorkingDate());


        ProcedureDTO procedure = procedureClient
                .findProcedureByIdAndMasterId(bookingRequestDto.getProcedureId(),
                        bookingRequestDto.getMasterId());

        return new AggregatedBooking(workingMasterDay, procedure);
    }

    @Override
    public BookingResponseDTO collectResponseData(Booking booking) {

        WorkingDayDto workingDay = workingDayClient.findById(booking.getWorkingDayId());
        ProcedureDTO procedure = procedureClient.findProcedureByIdAndMasterId(booking.getProcedureId(),
                workingDay.getMasterId());

        UserDTO client = userClient.getUserById(booking.getClientId());
        UserDTO master = userClient.getUserById(workingDay.getMasterId());

        return bookingMapper.toBookingResponseDTO(client, master, procedure, booking, workingDay);
    }


}
