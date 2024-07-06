package dajava.dacs.repository;

import dajava.dacs.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface FacultyRepository extends JpaRepository<Faculty, String> {

}
