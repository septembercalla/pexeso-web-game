package sk.tuke.kpi.kp.pexeso.service.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sk.tuke.kpi.kp.pexeso.entity.Score;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByGame(String game);

    @Query("SELECT AVG(s.points) FROM Score s WHERE s.game = :game")
    Double findAverageScoreByGame(@Param("game") String game);
}
