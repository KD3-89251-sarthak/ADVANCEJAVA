package assign05.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import assign05.utils.DbUtil;

public class VoteDao {
    
    public boolean hasUserVoted(int userId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM votes WHERE user_id = ?";
        try (Connection con = DbUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    public int castVote(int userId, int candidateId) throws SQLException {
        Connection con = null;
        try {
            con = DbUtil.getConnection();
            con.setAutoCommit(false);
            
            // Check if user has already voted
            if (hasUserVoted(userId)) {
                con.rollback();
                return 0; // User has already voted
            }
            
            // Insert vote record
            String insertVoteSql = "INSERT INTO votes(user_id, candidate_id) VALUES(?, ?)";
            try (PreparedStatement stmt1 = con.prepareStatement(insertVoteSql)) {
                stmt1.setInt(1, userId);
                stmt1.setInt(2, candidateId);
                stmt1.executeUpdate();
            }
            
            // Update candidate vote count
            String updateCandidateSql = "UPDATE candidates SET votes = votes + 1 WHERE id = ?";
            try (PreparedStatement stmt2 = con.prepareStatement(updateCandidateSql)) {
                stmt2.setInt(1, candidateId);
                stmt2.executeUpdate();
            }
            
            con.commit();
            return 1; // Success
            
        } catch (SQLException e) {
            if (con != null) {
                con.rollback();
            }
            throw e;
        } finally {
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
    }
    
    public int getTotalVotes() throws SQLException {
        String sql = "SELECT COUNT(*) FROM votes";
        try (Connection con = DbUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
}

