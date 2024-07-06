package dajava.dacs.repository;


import dajava.dacs.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    @Query("SELECT r.id FROM Role r WHERE r.name =?1")
    Long getRoleIdByName(String roleName);
}
