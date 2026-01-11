import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BidDAO {

    public List<Double> getBidsForItem(String itemName) {
        List<Double> bids = new ArrayList<>();
        String query = "SELECT b.amount FROM bids b JOIN items i ON b.item_id = i.id WHERE i.name = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, itemName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bids.add(rs.getDouble("amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bids;
    }

    public void placeBid(String itemName, int userId, double amount) {
        String getItemId = "SELECT id FROM items WHERE name = ?";
        String insertBid = "INSERT INTO bids (item_id, user_id, amount) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement getItemStmt = conn.prepareStatement(getItemId)) {

            getItemStmt.setString(1, itemName);
            ResultSet rs = getItemStmt.executeQuery();
            if (rs.next()) {
                int itemId = rs.getInt("id");
                try (PreparedStatement insertStmt = conn.prepareStatement(insertBid)) {
                    insertStmt.setInt(1, itemId);
                    insertStmt.setInt(2, userId);
                    insertStmt.setDouble(3, amount);
                    insertStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

