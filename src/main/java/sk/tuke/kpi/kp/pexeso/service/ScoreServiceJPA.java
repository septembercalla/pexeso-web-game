//package sk.tuke.kpi.kp.pexeso.service;
//
//import org.springframework.stereotype.Service;
//import sk.tuke.kpi.kp.pexeso.entity.Score;
//import sk.tuke.kpi.kp.pexeso.service.JPA.ScoreRepository;
//
//@Service
//public class ScoreServiceJPA {
//    private final ScoreRepository scoreRepository;
//
//    public ScoreServiceJPA(ScoreRepository scoreRepository) {
//        this.scoreRepository = scoreRepository;
//    }
//
//    public void addScore(String player, String game, int points) {
//        Score score = new Score(player, game, points);
//        scoreRepository.save(score);
//    }
//}
package sk.tuke.kpi.kp.pexeso.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sk.tuke.kpi.kp.pexeso.entity.Score;
import sk.tuke.kpi.kp.pexeso.service.JPA.ScoreRepository;

import java.util.List;

@Service
public class ScoreServiceJPA {
    private final ScoreRepository scoreRepository;

    public ScoreServiceJPA(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public void addScore(String player, String game, int points) {
        Score score = new Score(player, game, points);
        scoreRepository.save(score);
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll(Sort.by(Sort.Direction.DESC, "points"));
    }
}
