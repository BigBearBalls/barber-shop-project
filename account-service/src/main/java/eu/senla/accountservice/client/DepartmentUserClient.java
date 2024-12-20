package eu.senla.accountservice.client;

import eu.senla.common.department.dto.response.DepartmentUserDTO;
import eu.senla.common.department.dto.response.ShortDepartmentUserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(url = "${feign.clients.department-service.url}", name = "departmentUserClient")
public interface DepartmentUserClient {

    @GetMapping("/internal/users/")
    DepartmentUserDTO getUser();

    @GetMapping("/internal/users/{userId}")
    DepartmentUserDTO getUserById(@PathVariable("userId") UUID userId);

    @GetMapping("/internal/users/{userId}/preview")
    ShortDepartmentUserInfoDTO getShortUserInfo(@PathVariable("userId") UUID userId);
}
