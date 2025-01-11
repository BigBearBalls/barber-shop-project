package eu.senla.booking.client;

import eu.senla.common.department.dto.response.DepartmentUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(url = "${feign.clients.department-service.url}", name = "departmentClient")
public interface DepartmentClient {

    @GetMapping("/internal/users/")
    DepartmentUserDTO getUser();
    @GetMapping("/internal/users/{userId}")
    DepartmentUserDTO getUserById(@PathVariable("userId") UUID userId);
}
