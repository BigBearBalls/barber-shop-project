package eu.senla.bookingservice.service.impl;

//import eu.senla.common.booking.dto.request.AggregatedBooking;
//import eu.senla.common.booking.dto.request.BookingRequestDTO;
import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.junit.jupiter.MockitoExtension;

        import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

//    @Mock
//    private BookingRepository bookingRepository;
//
//    @Mock
//    private BookingMapper bookingMapper;
//
//    @InjectMocks
//    private BookingServiceImpl bookingService;
//
//    @Test
//    void saveBooking_shouldSaveAndReturnIdResponseDTO() {
//
//        BookingRequestDTO bookingRequestDTO = new BookingRequestDTO(UUID.randomUUID(), UUID.randomUUID(),
//                UUID.randomUUID(), LocalTime.of(10, 30), LocalDate.of(2024, 12, 15));
//
//        ProcedureDTO procedure = new ProcedureDTO(UUID.randomUUID(), "Стрижка",
//                new BigDecimal(100), 30);
//
//        ResponseWorkingDayDTO workingDay = new ResponseWorkingDayDTO(UUID.randomUUID(), UUID.randomUUID(),
//                LocalDate.of(2024, 12, 15), LocalTime.of(10, 0),
//                LocalTime.of(18, 0));
//
//        Booking booking = new Booking(UUID.randomUUID(), UUID.randomUUID(), LocalTime.of(10, 0),
//                LocalTime.of(10, 30), UUID.randomUUID(), UUID.randomUUID());
//
//        when(bookingRepository.findAllByWorkingDayId(workingDay.getId())).thenReturn(List.of());
//        when(bookingMapper.toBooking(bookingRequestDTO, procedure, workingDay, bookingRequestDTO.getReservationStart()
//                .plusMinutes(procedure.getDuration()))).thenReturn(booking);
//        when(bookingRepository.save(booking)).thenReturn(booking);
//
//        IdResponseDTO idResponseDTO = bookingService.saveBooking(new AggregatedBooking(workingDay, procedure, bookingRequestDTO));
//
//        verify(bookingRepository, times(1)).findAllByWorkingDayId(workingDay.getId());
//        verify(bookingMapper, times(1)).toBooking(bookingRequestDTO, procedure, workingDay, bookingRequestDTO.getReservationStart()
//                .plusMinutes(procedure.getDuration()));
//        verify(bookingRepository, times(1)).save(booking);
//        assertEquals(idResponseDTO.getId(), booking.getId());
//
//    }
//
//    @Test
//    void findBookingById_shouldReturnBooking() {
//
//        UUID randomId = UUID.randomUUID();
//
//        Booking booking = new Booking(randomId, UUID.randomUUID(), LocalTime.of(10, 0),
//                LocalTime.of(10, 30), UUID.randomUUID(), UUID.randomUUID());
//
//        when(bookingRepository.findById(randomId)).thenReturn(Optional.of(booking));
//
//        verify(bookingRepository, times(0)).findById(randomId);
//        assertEquals(booking, bookingService.findBookingById(randomId));
//    }
//
//    @Test
//    void findBookingById_shouldReturnException() {
//
//        UUID randomId = UUID.randomUUID();
//
//        when(bookingRepository.findById(randomId)).thenReturn(Optional.empty());
//
//        NotFoundException exception = assertThrows(
//                NotFoundException.class,
//                () -> bookingService.findBookingById(randomId),
//                "Expected to throw ResourceNotFoundException, but it didn't"
//        );
//
//        verify(bookingRepository, times(1)).findById(randomId);
//        assertEquals(String.format("Booking with ID %s was not found!", randomId), exception.getMessage());
//    }

}