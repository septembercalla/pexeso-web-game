package sk.tuke.kpi.kp.pexeso.database;

import java.util.ArrayList;
import java.util.List;

public class RatingService {
    private final List<Integer> ratings;

    public RatingService() {
        this.ratings = new ArrayList<>();
    }

    public void addRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            ratings.add(rating);
        } else {
            System.out.println("Invalid rating! Please enter a value between 1 and 5.");
        }
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0;
        }
        int sum = ratings.stream().mapToInt(Integer::intValue).sum();
        return (double) sum / ratings.size();
    }

    public List<Integer> getRatings() {
        return new ArrayList<>(ratings);
    }
}
