package dajava.dacs.repository;

import dajava.dacs.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, String> {
    // Add custom query methods if needed
}
