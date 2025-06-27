package sk.tuke.kpi.kp.pexeso.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.kpi.kp.pexeso.entity.Comment;
import sk.tuke.kpi.kp.pexeso.service.CommentServiceJPA;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentServiceRest {

    private final CommentServiceJPA commentService;

    @Autowired
    public CommentServiceRest(CommentServiceJPA commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{game}")
    public List<Comment> getCommentsByGame(@PathVariable("game") String game) {
        return commentService.getCommentsForGame(game);
    }

    @PostMapping
    public void addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
    }
}
