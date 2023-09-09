package az.ramazan.employeservice.service.impl;

import az.ramazan.employeservice.dto.EmployeeDto;
import az.ramazan.employeservice.entity.EmployeeEntity;
import az.ramazan.employeservice.repository.EmployeeRepository;
import az.ramazan.employeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employee = new EmployeeEntity(
                employeeDto.getId(),
                employeeDto.getEmail(),
                employeeDto.getFirstName(),
                employeeDto.getLastName()

        );
        EmployeeEntity savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail()
        );
        return savedEmployeeDto;


    }

    @Override
    public EmployeeDto getEmployeeById(Long employeId) {
        EmployeeEntity employee = employeeRepository.findById(employeId).get();
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
        return employeeDto;
    }

}

