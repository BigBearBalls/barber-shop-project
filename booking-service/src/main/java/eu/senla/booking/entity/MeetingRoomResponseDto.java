<<<<<<<< HEAD:libs/common/department/src/main/java/eu/senla/common/department/dto/request/CreateDepartmentUserRequest.java
package eu.senla.common.department.dto.request;
========
package eu.senla.booking.entity;
>>>>>>>> origin/BPS-bookingservice-103:booking-service/src/main/java/eu/senla/booking/entity/MeetingRoomResponseDto.java

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
<<<<<<<< HEAD:libs/common/department/src/main/java/eu/senla/common/department/dto/request/CreateDepartmentUserRequest.java
public class CreateDepartmentUserRequest {
    private UUID id;
========
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRoomResponseDto {

    private Integer number;
>>>>>>>> origin/BPS-bookingservice-103:booking-service/src/main/java/eu/senla/booking/entity/MeetingRoomResponseDto.java
}
