package eu.senla.authservice.client;

import eu.senla.common.department.dto.request.CreateDepartmentUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(url = "${feign.clients.department-service.url}", name = "departmentUserClient")
public interface DepartmentUserClient {

    @PostMapping(value = "/internal/users/")
    @ResponseStatus(HttpStatus.CREATED)
    void createUser(@RequestBody CreateDepartmentUserRequest request);
}
