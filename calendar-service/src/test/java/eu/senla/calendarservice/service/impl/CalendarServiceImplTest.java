package eu.senla.calendarservice.service.impl;

import eu.senla.calendarservice.entity.DayOff;
import eu.senla.calendarservice.mapper.CalendarMapper;
import eu.senla.calendarservice.repository.CalendarRepository;
import eu.senla.httpconfiguration.exceptioncontroller.exception.InvalidValueException;
import eu.senla.httpconfiguration.exceptioncontroller.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalendarServiceImplTest {


    @Mock
    private CalendarMapper calendarMapper;

    @Mock
    private CalendarRepository calendarRepository;

    @InjectMocks
    private CalendarServiceImpl calendarService;

    @Test
    void setDayOff_shouldReturnSavedDate() {

        LocalDate correctDate = LocalDate.of(2025, 5, 3);
        DayOff dayOff = new DayOff(1, correctDate);

        when(calendarMapper.toCalendarDayOff(correctDate)).thenReturn(dayOff);
        when(calendarRepository.save(dayOff)).thenReturn(dayOff);

        LocalDate savedDate = calendarService.setDayOff(correctDate);

        verify(calendarMapper, times(1)).toCalendarDayOff(correctDate);
        verify(calendarRepository, times(1)).save(dayOff);
        assertEquals(correctDate, savedDate);
    }

    @Test
    void setDayOff_shouldReturnDateBeforeNowError() {
        LocalDate pastDate = LocalDate.of(2022, 5, 3);

        InvalidValueException exception = assertThrows(
                InvalidValueException.class,
                () -> calendarService.setDayOff(pastDate),
                "Expected validateDate to throw InvalidDateException, but it didn't"
        );

        assertEquals("Date cannot be in the past!", exception.getMessage());

    }

    @Test
    void setWorkingDay_shouldReturnException() {

        LocalDate notExistingDate = LocalDate.of(2025, 6, 6);

        when(calendarRepository.existsByDate(notExistingDate)).thenReturn(false);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> calendarService.cancelDayOff(notExistingDate),
                "Expected not exist date to throw InvalidDateException, but it didn't"
        );

        assertEquals(String.format("This day (%s) is not day off!", notExistingDate), exception.getMessage());
    }

    @Test
    void checkDay_shouldReturnPositiveValue() {

        LocalDate holidayDate = LocalDate.of(2025, 1, 1);

        when(calendarRepository.existsByDate(holidayDate)).thenReturn(true);

        Boolean result = calendarService.checkDay(holidayDate);
        verify(calendarRepository, times(1)).existsByDate(holidayDate);
        assertTrue(result);
    }

    @Test
    void checkDay_shouldReturnNegativeValue() {

        LocalDate workingDate = LocalDate.of(2025, 10, 10);

        when(calendarRepository.existsByDate(workingDate)).thenReturn(false);

        Boolean result = calendarService.checkDay(workingDate);
        verify(calendarRepository, times(1)).existsByDate(workingDate);
        assertFalse(result);
    }
}
