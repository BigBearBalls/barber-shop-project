package eu.senla.booking.service.impl;

import eu.senla.booking.dto.request.AggregatedBooking;
import eu.senla.booking.dto.request.BookingRequestDTO;
import eu.senla.booking.dto.response.BookingResponseDTO;
import eu.senla.booking.entity.Booking;
import eu.senla.booking.entity.WorkingDay;
import eu.senla.booking.mock.ProcedureMock;
import eu.senla.booking.mock.ProcedureServiceMock;
import eu.senla.booking.mock.UserMock;
import eu.senla.booking.mock.UserServiceMock;
import eu.senla.booking.service.BookingDataAggregator;
import eu.senla.booking.service.WorkingDayService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingDataAggregatorImpl implements BookingDataAggregator {

    private final WorkingDayService workingDayService;
    private final ProcedureServiceMock procedureServiceMock;
    private final UserServiceMock userServiceMock;


    @Override
    public AggregatedBooking collectDataForSaving(BookingRequestDTO bookingRequestDto) {
        WorkingDay workingMasterDay = workingDayService
                .findWorkingDayByMasterAndWorkingDate(bookingRequestDto.masterId(),
                        bookingRequestDto.workingDate());

        ProcedureMock procedure = procedureServiceMock
                .findProcedureByIdAndMasterId(bookingRequestDto.procedureId(),
                        bookingRequestDto.masterId());

        return new AggregatedBooking(workingMasterDay, procedure);
    }

    @Override
    public BookingResponseDTO collectResponseData(Booking booking) {

        WorkingDay workingDay = workingDayService.findById(booking.getWorkingDayId());
        ProcedureMock procedure = procedureServiceMock.findProcedureByIdAndMasterId(booking.getProcedureId(),
                workingDay.getMaster());

        UserMock client = userServiceMock.findUserById(booking.getClientId());
        UserMock master = userServiceMock.findUserById(workingDay.getMaster());

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
