package eu.senla.booking.facade.impl;

import eu.senla.booking.client.ProcedureClient;
import eu.senla.booking.client.UserClient;
import eu.senla.booking.client.WorkingDayClient;
import eu.senla.booking.data.mapper.BookingResponseMapper;
import eu.senla.booking.entity.Booking;
import eu.senla.booking.facade.BookingDataAggregator;
import eu.senla.common.booking.dto.request.AggregatedBooking;
import eu.senla.common.booking.dto.request.BookingRequestDTO;
import eu.senla.common.booking.dto.response.BookingResponseDTO;
import eu.senla.common.booking.dto.response.ResponseWorkingDayDTO;
import eu.senla.common.dto.ProcedureDTO;
import eu.senla.common.dto.UserDataDTO;
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
        ResponseWorkingDayDTO workingMasterDay = workingDayClient.findByMasterIdAndWorkingDate(bookingRequestDto.getMasterId(),
                bookingRequestDto.getWorkingDate());


        ProcedureDTO procedure = procedureClient
                .findProcedureByIdAndMasterId(bookingRequestDto.getProcedureId(),
                        bookingRequestDto.getMasterId());

        return new AggregatedBooking(workingMasterDay, procedure, bookingRequestDto);
    }

    @Override
    public BookingResponseDTO collectResponseData(Booking booking) {

        ResponseWorkingDayDTO workingDay = workingDayClient.findById(booking.getWorkingDayId());
        ProcedureDTO procedure = procedureClient.findProcedureByIdAndMasterId(booking.getProcedureId(),
                workingDay.getMasterId());

        UserDataDTO client = userClient.getUserById(booking.getClientId());
        UserDataDTO master = userClient.getUserById(workingDay.getMasterId());

        return bookingMapper.toBookingResponseDTO(client, master, procedure, booking, workingDay);
    }


}
