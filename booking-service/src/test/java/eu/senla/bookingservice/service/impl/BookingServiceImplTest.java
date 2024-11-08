//package eu.senla.bookingservice.service.impl;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import eu.senla.booking.dto.request.BookingRequestDTO;
//import eu.senla.booking.dto.response.IdResponseDTO;
//import eu.senla.booking.entity.Booking;
//import eu.senla.booking.entity.WorkingDay;
//import eu.senla.booking.service.exception.ResourceNotFoundException;
//import eu.senla.booking.service.exception.TimeAlreadyBookedException;
//import eu.senla.booking.repository.BookingRepository;
//import eu.senla.booking.service.impl.BookingServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@ExtendWith(MockitoExtension.class)
//public class BookingServiceImplTest {
//
//    @Mock
//    private BookingRepository bookingRepository;
//
//    @InjectMocks
//    private BookingServiceImpl bookingService;
//
//    private WorkingDay workingDay;
//    private Booking booking;
//
//    UUID randomId = UUID.randomUUID();
//
//    @BeforeEach
//    void setUp() {
//        workingDay = new WorkingDay();
//        workingDay.setId(1);
//        workingDay.setWorkStart(LocalTime.of(9, 0));
//        workingDay.setWorkEnd(LocalTime.of(17, 0));
//
//
//        booking = Booking.builder()
//                .id(1)
//                .clientId(randomId)
//                .procedureId(1)
//                .workingDayId(workingDay.getId())
//                .reservationStart(LocalTime.of(10,0))
//                .reservationEnd(LocalTime.of(11,0))
//                .build();
//    }
//
////    @Test
////    @Transactional
////    void testSaveBookingSuccessfully() {
////        when(bookingRepository.findAllByWorkingDayId(workingDay.getId())).thenReturn(List.of());
////        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
////        BookingRequestDTO bookingRequestDTO  = new BookingRequestDTO(1, 1, randomId);
////        IdResponseDTO response = bookingService.saveBooking(workingDay, 1);
////
////        assertNotNull(response);
////        assertEquals(1, response.id());
////        verify(bookingRepository, times(1)).save(any(Booking.class));
////    }
////
////    @Test
////    void testSaveBookingThrowsTimeAlreadyBookedException() {
////        Booking existingBooking = Booking.builder()
////                .reservationStart(LocalTime.of(10, 0))
////                .reservationEnd(LocalTime.of(11, 0))
////                .build();
////
////        when(bookingRepository.findAllByWorkingDayId(workingDay.getId())).thenReturn(List.of(existingBooking));
////
////        TimeAlreadyBookedException exception = assertThrows(TimeAlreadyBookedException.class, () ->
////                bookingService.saveBooking(() -> workingDay, bookingRequestDTO));
////
////        assertEquals("Time already booked", exception.getMessage());
////    }
//
////    @Test
////    void testFindBookingByIdSuccessfully() {
////        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));
////
////        Booking foundBooking = bookingService.findBookingById(1);
////
////        assertNotNull(foundBooking);
////        assertEquals(1, foundBooking.getId());
////        verify(bookingRepository, times(1)).findById(1);
////    }
//
//    @Test
//    void testFindBookingByIdThrowsResourceNotFoundException() {
//        when(bookingRepository.findById(1)).thenReturn(Optional.empty());
//
//        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
//                bookingService.findBookingById(1));
//
//        assertEquals("There are no any bookings with id: 1", exception.getMessage());
//    }
//}
