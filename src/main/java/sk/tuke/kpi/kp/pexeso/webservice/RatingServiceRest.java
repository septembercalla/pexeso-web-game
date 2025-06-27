package sk.tuke.kpi.kp.pexeso.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.kpi.kp.pexeso.entity.Rating;
import sk.tuke.kpi.kp.pexeso.service.JPA.RatingRepository;

import java.util.*;

@RestController
@RequestMapping("/api/ratings")
public class RatingServiceRest {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingServiceRest(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }
    @PostMapping
    public void addRating(@RequestBody Rating rating) {
        ratingRepository.save(rating);
    }

    @GetMapping("/{game}")
    public Map<String, Object> getRatingsByGame(@PathVariable String game) {
        List<Rating> ratings = ratingRepository.findByGame(game);

        double average = ratings.stream()
                .mapToInt(Rating::getRating)
                .average()
                .orElse(0.0);

        List<Map<String, Object>> simplified = new ArrayList<>();
        for (Rating r : ratings) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("id", r.getId());
            entry.put("player", r.getPlayer());
            entry.put("game", r.getGame());
            entry.put("rating", r.getRating());
            simplified.add(entry);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("ratings", simplified);
        response.put("average", average);

        return response;
    }
}
