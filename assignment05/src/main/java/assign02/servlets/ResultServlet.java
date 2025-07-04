package assign05.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import assign05.daos.CandidateDao;
import assign05.entities.Candidate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        HttpSession session = req.getSession();
        String userRole = (String) session.getAttribute("role");
        
        try {
            CandidateDao candidateDao = new CandidateDao();
            List<Candidate> candidates = candidateDao.findAll();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Election Results - Online Voting System</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; max-width: 800px; margin: 20px auto; padding: 20px; background-color: #f5f5f5; }");
            out.println(".container { background-color: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
            out.println("h2 { text-align: center; color: #333; margin-bottom: 30px; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-bottom: 30px; }");
            out.println("th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }");
            out.println("th { background-color: #007bff; color: white; }");
            out.println("tr:hover { background-color: #f5f5f5; }");
            out.println(".btn { background-color: #28a745; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin: 5px; display: inline-block; }");
            out.println(".btn:hover { background-color: #218838; }");
            out.println(".btn-primary { background-color: #007bff; }");
            out.println(".btn-primary:hover { background-color: #0056b3; }");
            out.println(".actions { text-align: center; margin-top: 20px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h2>Election Results</h2>");
            
            if (candidates.isEmpty()) {
                out.println("<p style='text-align: center; color: #666;'>No candidates found.</p>");
            } else {
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>Rank</th>");
                out.println("<th>Candidate Name</th>");
                out.println("<th>Party</th>");
                out.println("<th>Votes</th>");
                if ("admin".equals(userRole)) {
                    out.println("<th>Actions</th>");
                }
                out.println("</tr>");
                
                int rank = 1;
                for (Candidate candidate : candidates) {
                    out.println("<tr>");
                    out.println("<td>" + rank++ + "</td>");
                    out.println("<td>" + candidate.getName() + "</td>");
                    out.println("<td>" + candidate.getParty() + "</td>");
                    out.println("<td>" + candidate.getVotes() + "</td>");
                    if ("admin".equals(userRole)) {
                        out.println("<td>");
                        out.println("<a href='edit?id=" + candidate.getId() + "' class='btn btn-primary'>Edit</a>");
                        out.println("</td>");
                    }
                    out.println("</tr>");
                }
                out.println("</table>");
            }
            
            out.println("<div class='actions'>");
            if ("admin".equals(userRole)) {
                out.println("<a href='newcandidate.html' class='btn'>Add New Candidate</a>");
            }
            out.println("<a href='index.html' class='btn btn-primary'>Back to Home</a>");
            out.println("</div>");
            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h3>Database Error: " + e.getMessage() + "</h3>");
        }
    }
}

