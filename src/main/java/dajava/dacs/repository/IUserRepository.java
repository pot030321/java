package dajava.dacs.repository;


import dajava.dacs.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByResetPasswordToken(String token);
    @Query("SELECT u FROM User u Where u.username=?1")
    User findByUserName(String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_role(user_id, role_id) VALUE(?1, ?2)", nativeQuery = true)
    void addRoleTOUser(Long userId, Long roleId);

    @Query("SELECT u.id FROM User u WHERE u.username=?1")
    Long getUserIdByUsername(String username);

    @Query(value="SELECT r.name FROM role r INNER JOIN user_role ur On r.id = ur.role_id WHERE ur.user_id = ?1",
            nativeQuery = true)
    String[] getRoleOfUser(Long userId);


}
