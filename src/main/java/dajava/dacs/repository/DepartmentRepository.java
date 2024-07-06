package dajava.dacs.repository;

import dajava.dacs.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    // Add custom query methods if needed
}
