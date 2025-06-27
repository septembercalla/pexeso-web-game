package sk.tuke.kpi.kp.pexeso.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseService {

    private static void executeUpdate(String sql, Object... params) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof String) {
                    stmt.setString(i + 1, (String) params[i]);
                } else if (params[i] instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) params[i]);
                }
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static void addScore(String player, String game, int score) {
        String sql = "INSERT INTO score (player, game, points, played_on) VALUES (?, ?, ?, NOW())";
        executeUpdate(sql, player, game, score);
    }

    public static void addComment(String player, String game, String comment, int score) {
        String sql = "INSERT INTO comment (player, game, comment, score) VALUES (?, ?, ?, ?)";
        executeUpdate(sql, player, game, comment, score);
    }

    public static void addRating(String player, String game, int rating) {
        String sql = "INSERT INTO rating (player, game, rating, rated_on) VALUES (?, ?, ?, NOW())";
        executeUpdate(sql, player, game, rating);
    }
}
