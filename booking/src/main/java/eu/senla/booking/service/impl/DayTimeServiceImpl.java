package eu.senla.booking.service.impl;

import eu.senla.booking.repository.DayTimeRepository;
import eu.senla.booking.service.DayTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DayTimeServiceImpl implements DayTimeService {

    private final DayTimeRepository dayTimeRepository;

}
