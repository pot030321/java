package dajava.dacs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dajava.dacs.model.SubJect;
import dajava.dacs.repository.SubJectRepository;

import javax.security.auth.Subject;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubJectRepository subjectRepository;

    public List<SubJect> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<SubJect> getSubjectById(String id) {
        return subjectRepository.findById(id);
    }

    public SubJect saveSubject(SubJect subject) {
        return subjectRepository.save(subject);
    }

    public void deleteSubject(String id) {
        subjectRepository.deleteById(id);
    }
}

