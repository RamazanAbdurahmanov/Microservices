package az.ramazan.employeservice.service;

import az.ramazan.employeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080",value="DEPARTMENT-SERVICE")
public interface APIClient {
    //Build Get Department Rest Api
    @GetMapping("api/departments/get/{department-code}")
    DepartmentDto getDepartment(@PathVariable(name = "department-code") String departmentCode);

}
