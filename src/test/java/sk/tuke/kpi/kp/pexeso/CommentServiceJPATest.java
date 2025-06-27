// package sk.tuke.kpi.kp.pexeso;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.test.annotation.Rollback;
// import sk.tuke.kpi.kp.pexeso.entity.Comment;
// import sk.tuke.kpi.kp.pexeso.service.CommentServiceJPA;

// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest
// @Transactional
// @Rollback
// public class CommentServiceJPATest {

//     @Autowired
//     private CommentServiceJPA commentService;

//     @Test
//     public void testAddAndGetComments() {
//         Comment comment = new Comment("TestUser", "Pexeso", "Great game!", 5);
//         commentService.addComment(comment);

//         List<Comment> comments = commentService.getCommentsForGame("Pexeso");
//         Comment lastComment = comments.get(comments.size() - 1);

//         assertEquals("TestUser", lastComment.getPlayer());
//         assertEquals("Great game!", lastComment.getComment());
//         assertEquals(5, lastComment.getScore());
//     }
// }
