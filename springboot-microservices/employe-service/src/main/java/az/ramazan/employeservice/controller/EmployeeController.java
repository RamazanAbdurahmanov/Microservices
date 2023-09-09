package az.ramazan.employeservice.controller;

import az.ramazan.employeservice.dto.EmployeeDto;
import az.ramazan.employeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController  {
    private  final EmployeeService employeeService;
    //Build save Employe REST API
    @PostMapping("create")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
       EmployeeDto savedEmployee= employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    //Build get Employee REST API
    @GetMapping("get/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable(name="id") Long employeeId){
        EmployeeDto employeeDto=employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);

    }



}
