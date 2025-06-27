package sk.tuke.kpi.kp.pexeso.service.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.tuke.kpi.kp.pexeso.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByGame(String game);
}
