package sk.tuke.kpi.kp.pexeso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.tuke.kpi.kp.pexeso.entity.Comment;
import sk.tuke.kpi.kp.pexeso.service.JPA.CommentRepository;

import java.util.List;

@Service
public class CommentServiceJPA {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceJPA(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> getCommentsForGame(String game) {
        return commentRepository.findByGame(game);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
