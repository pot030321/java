package dajava.dacs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dajava.dacs.model.Lecturer;
import dajava.dacs.repository.LecturerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerService {

    @Autowired
    private LecturerRepository lecturerRepository;

    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    public Optional<Lecturer> getLecturerById(String id) {
        return lecturerRepository.findById(id);
    }

    public Lecturer saveLecturer(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    public void deleteLecturer(String id) {
        lecturerRepository.deleteById(id);
    }
}

