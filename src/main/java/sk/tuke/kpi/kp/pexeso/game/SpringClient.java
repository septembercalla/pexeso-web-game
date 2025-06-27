package sk.tuke.kpi.kp.pexeso.game;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sk.tuke.kpi.kp.pexeso.AppConfig;
import sk.tuke.kpi.kp.pexeso.service.CommentServiceJPA;
import sk.tuke.kpi.kp.pexeso.service.RatingServiceJPA;
import sk.tuke.kpi.kp.pexeso.service.ScoreServiceJPA;


import java.util.Scanner;

public class SpringClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CommentServiceJPA commentService = context.getBean(CommentServiceJPA.class);
        RatingServiceJPA ratingService = context.getBean(RatingServiceJPA.class);
        ScoreServiceJPA scoreService = context.getBean(ScoreServiceJPA.class);

        System.out.println("\n=== Recent Comments ===");
        commentService.getCommentsForGame("Pexeso").forEach(c -> {
            System.out.println(c.getPlayer() + ": \"" + c.getComment() + "\" (rating: " + c.getRating() + ")");
        });
        System.out.println("=======================\n");

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        String playAgain;
        System.out.println("=== Карты на доске ===");


        do {
            Game game = new Game(2, playerName, commentService, ratingService, scoreService);

            while (!game.isGameOver()) {
                System.out.print("Enter card index (0-" + (game.getBoard().getCards().size() - 1) + "): ");
                int index = scanner.nextInt();
                game.selectCard(index);
            }

            System.out.println("Game over! Attempts: " + game.getAttempts() + ", Score: " + game.getScore());

            scanner.nextLine(); // flush newline
            System.out.print("Leave a comment about the game: ");
            String comment = scanner.nextLine();

            int rating;
            while (true) {
                System.out.print("Rate the game (1-5): ");
                if (scanner.hasNextInt()) {
                    rating = scanner.nextInt();
                    if (rating >= 1 && rating <= 5) break;
                } else {
                    scanner.next();
                }
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }

            game.saveGameResults(comment, rating);

            System.out.print("Do you want to play again? (Y/N): ");
            scanner.nextLine();
            playAgain = scanner.nextLine().trim().toUpperCase();
        } while (playAgain.equals("Y"));

        System.out.println("Thanks for playing!");
        context.close();
    }
}
