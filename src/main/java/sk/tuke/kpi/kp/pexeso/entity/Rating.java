package sk.tuke.kpi.kp.pexeso.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String player;
    private String game;
    private int rating;

    @Column(name = "rated_on")
    private LocalDateTime ratedOn = LocalDateTime.now();

    public Rating() {}

    public Rating(String player, String game, int rating) {
        this.player = player;
        this.game = game;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getRatedOn() {
        return ratedOn;
    }

    public void setRatedOn(LocalDateTime ratedOn) {
        this.ratedOn = ratedOn;
    }
}
