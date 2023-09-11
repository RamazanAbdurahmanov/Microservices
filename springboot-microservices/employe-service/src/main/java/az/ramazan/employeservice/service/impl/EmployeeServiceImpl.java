package az.ramazan.employeservice.service.impl;

import az.ramazan.employeservice.dto.APIResponseDto;
import az.ramazan.employeservice.dto.DepartmentDto;
import az.ramazan.employeservice.dto.EmployeeDto;
import az.ramazan.employeservice.entity.EmployeeEntity;
import az.ramazan.employeservice.repository.EmployeeRepository;
import az.ramazan.employeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private  EmployeeRepository employeeRepository;
    private  RestTemplate restTemplate;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employee = new EmployeeEntity(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()

        );
        EmployeeEntity savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode()
        );
        return savedEmployeeDto;


    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        EmployeeEntity employee = employeeRepository.findById(employeeId).get();
      ResponseEntity<DepartmentDto> responseEntity= restTemplate.getForEntity("http://localhost:8080/api/departments/get/"
              + employee.getDepartmentCode(), DepartmentDto.class);
     DepartmentDto departmentDto= responseEntity.getBody();
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );
        APIResponseDto apiResponseDto= new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

}

