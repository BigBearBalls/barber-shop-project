//package eu.senla.booking.facade.impl;
//
//import eu.senla.booking.entity.Booking;
//import eu.senla.booking.facade.BookingDataAggregator;
//import eu.senla.booking.facade.BookingFacade;
//import eu.senla.booking.service.BookingService;
//import eu.senla.common.booking.dto.request.AggregatedBooking;
//import eu.senla.common.booking.dto.request.BookingRequestDTO;
//import eu.senla.common.booking.dto.response.BookingResponseDTO;
//import eu.senla.common.booking.dto.response.IdResponseDTO;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Service
//@AllArgsConstructor
//public class BookingFacadeImpl implements BookingFacade {
//
//    private final BookingDataAggregator bookingDataAggregator;
//    private final BookingService bookingService;
//
//    @Override
//    public IdResponseDTO saveBooking(BookingRequestDTO bookingRequestDto) {
//
//        AggregatedBooking aggregatedBookingData = bookingDataAggregator
//                .collectDataForSaving(bookingRequestDto);
//
//        return bookingService.saveBooking(aggregatedBookingData);
//    }
//
//    @Override
//    public BookingResponseDTO findById(UUID id) {
//
//        Booking foundBooking = bookingService.findBookingById(id);
//
//        return bookingDataAggregator.collectResponseData(foundBooking);
//    }
//}
