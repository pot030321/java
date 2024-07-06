package dajava.dacs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dajava.dacs.model.PersonalUser;
import dajava.dacs.repository.PersonalUserRepository;

import java.util.List;

@Service
public class PersonalUserService {
    @Autowired
    private PersonalUserRepository personalUserRepository;

    public void saveEvent(PersonalUser personalUser) {
        personalUserRepository.save(personalUser);
    }

    public void deleteEvent(Long id) {
        personalUserRepository.deleteById(id);
    }
    public List<PersonalUser> getAllEvents() {
        return personalUserRepository.findAll();
    }
}
