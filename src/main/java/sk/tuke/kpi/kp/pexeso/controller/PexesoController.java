
package sk.tuke.kpi.kp.pexeso.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.tuke.kpi.kp.pexeso.entity.Comment;
import sk.tuke.kpi.kp.pexeso.game.Game;
import sk.tuke.kpi.kp.pexeso.service.CommentServiceJPA;
import sk.tuke.kpi.kp.pexeso.service.RatingServiceJPA;
import sk.tuke.kpi.kp.pexeso.service.ScoreServiceJPA;
import org.springframework.web.bind.support.SessionStatus;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@SessionAttributes({"playerName", "size"})
public class PexesoController {

    private Game game;
    private final CommentServiceJPA commentService;
    private final RatingServiceJPA ratingService;
    private final ScoreServiceJPA scoreService;

    public PexesoController(CommentServiceJPA commentService, RatingServiceJPA ratingService, ScoreServiceJPA scoreService) {
        this.commentService = commentService;
        this.ratingService = ratingService;
        this.scoreService = scoreService;
    }

    @GetMapping("/")
    public String showStartPage(Model model, HttpSession session) {
        model.addAttribute("playerName", session.getAttribute("playerName"));
        return "start";
    }

    @PostMapping("/start")
    public String startGame(@RequestParam int size, HttpSession session) {
        String playerName = (String) session.getAttribute("playerName");

        if (playerName == null || playerName.trim().isEmpty()) {
            return "redirect:/login";
        }

        session.setAttribute("size", size);
        game = new Game(size, playerName, commentService, ratingService, scoreService);
        return "redirect:/pexeso";
    }



//    @PostMapping("/chaos")
//    public String chaos(HttpSession session) {
//        var cards = game.getBoard().getCards();
//        List<Integer> closedIndexes = new ArrayList<>();
//        for (int i = 0; i < cards.size(); i++) {
//            if (cards.get(i).getState().name().equals("FACE_DOWN")) {
//                closedIndexes.add(i);
//            }
//        }
//        if (closedIndexes.size() >= 2) {
//            int idx1 = closedIndexes.get(0);
//            int idx2 = closedIndexes.get(1);
//            var tmp = cards.get(idx1);
//            cards.set(idx1, cards.get(idx2));
//            cards.set(idx2, tmp);
//        }
//        return "redirect:/pexeso";
//    }


        @PostMapping ("/Menu")
    public String gomenu(HttpSession session){
         session.invalidate();
        return "redirect:/";
    }


    //
   @GetMapping("/pexeso")
    public String showGame(Model model, HttpSession session) {
        String playerName = (String) session.getAttribute("playerName");
        Integer size = (Integer) session.getAttribute("size");

        if (playerName == null || size == null) {
            return "redirect:/";
        }

        if (game == null) {
            game = new Game(size, playerName, commentService, ratingService, scoreService);
        }
        //123
        var cards = game.getBoard().getCards();
        int gridSize = (int) Math.sqrt(cards.size());

        model.addAttribute("playerName", playerName);
        model.addAttribute("cards", cards);
        model.addAttribute("score", game.getScore());
        model.addAttribute("gridSize", gridSize);
       //time  model.addAttribute("time", game.getElapsedTime());

        if (game.isGameOver()) {
            session.setAttribute("score", game.getScore());
            return "redirect:/winner";
        }

        return "pexeso";
    }

    @GetMapping("/flip")
    public String flipCard(HttpSession session, @RequestParam int index) {
        if (game == null) {
            String playerName = (String) session.getAttribute("playerName");
            Object sizeAttr = session.getAttribute("size");
            int size = (sizeAttr instanceof Integer) ? (Integer) sizeAttr : 2;

            game = new Game(size, playerName, commentService, ratingService, scoreService);
        }
        game.selectCard(index);

        if (game.isAwaitingFlipBack()) {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            game.flipBackUnmatched();
        }

        if (game.isGameOver()) {
            game.saveGameResults("Victory!", 5);
            session.setAttribute("score", game.getScore());
            return "redirect:/winner";
        }

        return "redirect:/pexeso";
    }
    ////

    ///
    @GetMapping("/winner")
    public String showWinner(Model model, HttpSession session) {
        model.addAttribute("score", session.getAttribute("score"));
        model.addAttribute("playerName", session.getAttribute("playerName"));
        return "winner";
    }

    @PostMapping("/feedback")
    public String saveFeedback(@RequestParam String comment,
                               @RequestParam int rating,
                               HttpSession session,
                               Model model) {
        String playerName = (String) session.getAttribute("playerName");
        Integer scoreAttr = (Integer) session.getAttribute("score");

        if (playerName == null || scoreAttr == null) {
            model.addAttribute("error", "Session expired. Please play again.");
            return "redirect:/";
        }

        int score = scoreAttr;

        //scoreService.addScore(playerName, "Pexeso", score, elapsedTime); // добавь нужный параметр в метод

        scoreService.addScore(playerName, "Pexeso", score);
        commentService.addComment(new Comment(playerName, "Pexeso", comment, rating));
        ratingService.addRating(playerName, "Pexeso", rating);

        session.removeAttribute("playerName");
        session.removeAttribute("size");
        session.removeAttribute("score");
        game = null;

        return "redirect:/";
    }

    @PostMapping("/signout")
    public String signOut(HttpSession session, SessionStatus status) {
        status.setComplete();
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/restart")
    public String restartGame(HttpSession session) {
        game = null;
        session.invalidate();
        //return "redirect:/";
        return "redirect:/";

    }

    @GetMapping("/scores")
    public String viewScores(Model model) {
        model.addAttribute("scores", scoreService.getAllScores());
        return "scores";
    }
    @GetMapping("/comments")
    public String viewComments(Model model) {
        model.addAttribute("comments", commentService.getAllComments());
        return "comments";
    }

    @GetMapping("/ratings")
    public String showRatings(Model model) {
        model.addAttribute("averageRating", ratingService.getAverageRating("Pexeso"));
        model.addAttribute("ratings", ratingService.getAllRatings());
        return "ratings";
    }
}
