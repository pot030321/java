package dajava.dacs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dajava.dacs.model.Faculty;
import dajava.dacs.repository.FacultyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Optional<Faculty> getFacultyById(String id) {
        return facultyRepository.findById(id);
    }

    public void saveFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }

    public void deleteFaculty(String id) {
        facultyRepository.deleteById(id);
    }
}

