package sk.tuke.kpi.kp.pexeso.database;

import java.util.ArrayList;
import java.util.List;

public class CommentService {
    private final List<String> comments;

    public CommentService() {
        this.comments = new ArrayList<>();
    }

    public void addComment(String comment) {
        if (comment != null && !comment.trim().isEmpty()) {
            comments.add(comment);
        }
    }

    public List<String> getComments() {
        return new ArrayList<>(comments);
    }
}
