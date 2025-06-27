package sk.tuke.kpi.kp.pexeso.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.kpi.kp.pexeso.entity.Score;
import sk.tuke.kpi.kp.pexeso.service.JPA.ScoreRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.*;
import java.util.stream.Collectors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/scores")
public class ScoreServiceRest {

    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreServiceRest(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @GetMapping("/{game}")
    public Map<String, Object> getScoresByGame(@PathVariable String game) {
        List<Score> scores = scoreRepository.findByGame(game);

        List<Score> sorted = scores.stream()
                .sorted(Comparator.comparingInt(Score::getPoints).reversed())
                .collect(Collectors.toList());

        List<Map<String, Object>> simplified = new ArrayList<>();
        for (int i = 0; i < sorted.size(); i++) {
            Score score = sorted.get(i);
            Map<String, Object> entry = new HashMap<>();
            entry.put("id", score.getId());
            entry.put("player", score.getPlayer());
            entry.put("points", score.getPoints());
            entry.put("rating", i + 1);
            simplified.add(entry);
        }

        String winner = sorted.stream()
                .findFirst()
                .map(Score::getPlayer)
                .orElse("No scores yet");

        Map<String, Object> response = new HashMap<>();
        response.put("scores", simplified);
        response.put("winner", winner);
        return response;
    }


    @PostMapping
    public void addScore(@RequestBody Score score) {
        scoreRepository.save(score);
    }
}

