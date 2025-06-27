package sk.tuke.kpi.kp.pexeso.service;

import org.springframework.stereotype.Service;
import sk.tuke.kpi.kp.pexeso.entity.Rating;
import sk.tuke.kpi.kp.pexeso.service.JPA.RatingRepository;

import java.util.List;

@Service
public class RatingServiceJPA {
    private final RatingRepository ratingRepository;

    public RatingServiceJPA(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public void addRating(String player, String game, int rating) {
        Rating newRating = new Rating(player, game, rating);
        ratingRepository.save(newRating);
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public double getAverageRating(String game) {
        List<Rating> ratings = ratingRepository.findByGame(game);
        return ratings.stream()
                .mapToInt(Rating::getRating)
                .average()
                .orElse(0.0);
    }
}
