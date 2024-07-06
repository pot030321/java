package dajava.dacs.repository;

import dajava.dacs.model.SubJect;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface SubJectRepository extends JpaRepository<SubJect, String> {
    // Add custom query methods if needed
}
