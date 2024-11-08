package eu.senla.booking.facade.impl;

import eu.senla.booking.client.ProcedureClient;
import eu.senla.booking.client.UserClient;
import eu.senla.booking.dto.ProcedureDTO;
import eu.senla.booking.dto.UserDTO;
import eu.senla.booking.dto.request.AggregatedBooking;
import eu.senla.booking.dto.request.BookingRequestDTO;
import eu.senla.booking.dto.response.BookingResponseDTO;
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

        return BookingResponseDTO.builder()
                .clientFirstName(client.getFirstname())
                .clientLastName(client.getLastname())
                .masterFirstname(master.getFirstname())
                .masterLastName(master.getLastname())
                .procedure(procedure.getName())
                .price(procedure.getPrice().doubleValue())
                .duration(procedure.getDuration())
                .date(workingDay.getWorkingDate())
                .time(booking.getReservationStart())
                .build();
    }


}
