package az.ramazan.employeservice.service;

import az.ramazan.employeservice.dto.APIResponseDto;
import az.ramazan.employeservice.dto.EmployeeDto;
import org.springframework.stereotype.Service;


public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long employeeId);
}
