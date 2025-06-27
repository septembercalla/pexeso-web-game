package sk.tuke.kpi.kp.pexeso.game;

public class Player {
    private int score;

    public Player(String name) {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }
}

