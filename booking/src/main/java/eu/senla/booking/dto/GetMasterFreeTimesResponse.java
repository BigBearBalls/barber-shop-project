package eu.senla.booking.dto;

import java.util.List;

public record GetMasterFreeTimesResponse(List<MasterTimeTableDTO> list) {
}
