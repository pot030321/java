package dajava.dacs.repository;

import dajava.dacs.model.PersonalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PersonalUserRepository extends JpaRepository<PersonalUser, Long> {
    // Add custom query methods if needed
}
