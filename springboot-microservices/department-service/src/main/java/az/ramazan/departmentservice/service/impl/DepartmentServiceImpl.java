package az.ramazan.departmentservice.service.impl;

import az.ramazan.departmentservice.dto.DepartmentDto;
import az.ramazan.departmentservice.entity.DepartmentEntity;
import az.ramazan.departmentservice.repository.DepartmentRepository;
import az.ramazan.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        DepartmentEntity department = new DepartmentEntity(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
        DepartmentEntity savedDepartment = departmentRepository.save(department);
        DepartmentDto savedDepartmentDto = new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode()
        );
        return savedDepartmentDto;

    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        DepartmentEntity department=departmentRepository.findByDepartmentCode(departmentCode);
        DepartmentDto departmentDto= new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDto;
    }


}
