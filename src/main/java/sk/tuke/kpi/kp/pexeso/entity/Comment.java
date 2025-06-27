package sk.tuke.kpi.kp.pexeso.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String player;

    private String game;

    @Column(nullable = false)
    private String comment;

    private int rating;

    @Column(name = "commented_on")
    private LocalDateTime commentedOn;

    public Comment() {
        this.commentedOn = LocalDateTime.now();
    }

    public Comment(String player, String game, String comment, int rating) {
        this.player = player;
        this.game = game;
        this.comment = comment;
        this.rating = rating;
        this.commentedOn = LocalDateTime.now();
    }

    // Getters and setters

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getCommentedOn() {
        return commentedOn;
    }

    public void setCommentedOn(LocalDateTime commentedOn) {
        this.commentedOn = commentedOn;
    }
}
