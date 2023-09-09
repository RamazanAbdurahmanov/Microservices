package az.ramazan.employeservice.service;

import az.ramazan.employeservice.dto.EmployeeDto;
import org.springframework.stereotype.Service;


public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeId);
}
