package eu.senla.booking.repository;

import eu.senla.booking.entity.Booking;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findAllByBookingDateAndMeetingRoomId(LocalDate bookingDate, UUID meetingRoomId);

    Booking getBookingById(UUID id);

    @Query(value = """
        SELECT * FROM booking_service_schema.bookings b
        WHERE (:userId IS NULL OR b.user_id = :userId)
        AND (cast(:startDate as date) IS NULL OR b.booking_date >= cast(:startDate as date))
        AND (cast(:endDate as date) IS NULL OR b.booking_date <= cast(:endDate as date))
        """, nativeQuery = true)
    Page<Booking> findByParams(
            @Param("userId") UUID userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );
}
