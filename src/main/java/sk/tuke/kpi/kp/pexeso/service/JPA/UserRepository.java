package sk.tuke.kpi.kp.pexeso.service.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.tuke.kpi.kp.pexeso.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
