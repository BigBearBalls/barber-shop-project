package eu.senla.booking.service.impl;

import eu.senla.booking.repository.MasterFreeTimeRepository;
import eu.senla.booking.service.MasterFreeTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MasterFreeTimeServiceImpl implements MasterFreeTimeService {

    private final MasterFreeTimeRepository masterFreeTimeRepository;

}
