package sk.tuke.kpi.kp.pexeso.game;

import sk.tuke.kpi.kp.pexeso.entity.Comment;
import sk.tuke.kpi.kp.pexeso.service.CommentServiceJPA;
import sk.tuke.kpi.kp.pexeso.service.RatingServiceJPA;
import sk.tuke.kpi.kp.pexeso.service.ScoreServiceJPA;

import java.util.List;

public class Game {
   //time  private long startTime;
    private final Board board;
    private final Player player;
    private final String playerName;
    private final CommentServiceJPA commentService;
    private final RatingServiceJPA ratingService;
    private final ScoreServiceJPA scoreService;

    private int attempts;
    private Integer firstCardIndex = null;
    private Integer secondCardIndex = null;
    private boolean awaitingSecondFlipReveal = false;

    public Game(int size, String playerName, CommentServiceJPA commentService,
                RatingServiceJPA ratingService, ScoreServiceJPA scoreService) {
        this.board = new Board(size);
        this.player = new Player(playerName);
        this.playerName = playerName;
        this.commentService = commentService;
        this.ratingService = ratingService;
        this.scoreService = scoreService;
        this.attempts = 0;
    //    this.startTime = System.currentTimeMillis();
    }

    public Board getBoard() {
        return board;
    }


public void selectCard(int index) {
    List<Card> cards = board.getCards();

    //
    if (firstCardIndex != null && secondCardIndex != null && awaitingSecondFlipReveal) {
        cards.get(firstCardIndex).flip();
        cards.get(secondCardIndex).flip();
        firstCardIndex = null;
        secondCardIndex = null;
        awaitingSecondFlipReveal = false;
        attempts++;
    }

    if (index < 0 || index >= cards.size()) return;

    Card selectedCard = cards.get(index);
    if (selectedCard.getState() != CardState.FACE_DOWN) return;

    selectedCard.flip();

    if (firstCardIndex == null) {
        firstCardIndex = index;
    } else {
        secondCardIndex = index;

        Card firstCard = cards.get(firstCardIndex);
        Card secondCard = selectedCard;

        if (firstCard.isMatched(secondCard)) {
            firstCard.markMatched();
            secondCard.markMatched();
            player.increaseScore();
            firstCardIndex = null;
            secondCardIndex = null;
        } else {
            awaitingSecondFlipReveal = true; // будет обработано в следующем ходе
        }
    }
}


    public boolean isAwaitingFlipBack() {
        return awaitingSecondFlipReveal;
    }
    public void flipBackUnmatched() {
    }
//time
//    public String getElapsedTime() {
//        long seconds = (System.currentTimeMillis() - startTime) / 1000;
//        long min = seconds / 60;
//        long sec = seconds % 60;
//        return String.format("%02d:%02d", min, sec);
//    }

    public boolean isGameOver() {
        return board.getCards().stream().allMatch(c -> c.getState() == CardState.MATCHED);
    }

    public int getAttempts() {
        return attempts;
    }

    public int getScore() {
        return player.getScore();
    }

    public void saveGameResults(String comment, int rating) {
        scoreService.addScore(playerName, "Pexeso", player.getScore());
    }

}
