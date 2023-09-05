package az.ramazan.departmentservice.repository;

import az.ramazan.departmentservice.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}
