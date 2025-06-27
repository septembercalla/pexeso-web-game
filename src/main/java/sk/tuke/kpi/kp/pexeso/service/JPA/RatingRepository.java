package sk.tuke.kpi.kp.pexeso.service.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.tuke.kpi.kp.pexeso.entity.Rating;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByGame(String game);
}
