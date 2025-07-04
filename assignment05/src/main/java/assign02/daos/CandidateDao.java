package assign05.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import assign05.entities.Candidate;
import assign05.utils.DbUtil;

public class CandidateDao {
    
    public int save(Candidate candidate) throws SQLException {
        String sql = "INSERT INTO candidates(name, party, votes) VALUES(?,?,?)";
        try (Connection con = DbUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, candidate.getName());
            stmt.setString(2, candidate.getParty());
            stmt.setInt(3, candidate.getVotes());
            
            return stmt.executeUpdate();
        }
    }
    
    public List<Candidate> findAll() throws SQLException {
        List<Candidate> candidates = new ArrayList<>();
        String sql = "SELECT * FROM candidates ORDER BY votes DESC";
        try (Connection con = DbUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Candidate candidate = new Candidate();
                candidate.setId(rs.getInt("id"));
                candidate.setName(rs.getString("name"));
                candidate.setParty(rs.getString("party"));
                candidate.setVotes(rs.getInt("votes"));
                candidates.add(candidate);
            }
        }
        return candidates;
    }
    
    public Candidate findById(int id) throws SQLException {
        String sql = "SELECT * FROM candidates WHERE id = ?";
        try (Connection con = DbUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Candidate candidate = new Candidate();
                    candidate.setId(rs.getInt("id"));
                    candidate.setName(rs.getString("name"));
                    candidate.setParty(rs.getString("party"));
                    candidate.setVotes(rs.getInt("votes"));
                    return candidate;
                }
            }
        }
        return null;
    }
    
    public int update(Candidate candidate) throws SQLException {
        String sql = "UPDATE candidates SET name=?, party=?, votes=? WHERE id=?";
        try (Connection con = DbUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, candidate.getName());
            stmt.setString(2, candidate.getParty());
            stmt.setInt(3, candidate.getVotes());
            stmt.setInt(4, candidate.getId());
            
            return stmt.executeUpdate();
        }
    }
    
    public int deleteById(int id) throws SQLException {
        String sql = "DELETE FROM candidates WHERE id = ?";
        try (Connection con = DbUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        }
    }
}

