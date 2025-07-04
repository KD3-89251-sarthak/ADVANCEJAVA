package assign05.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import assign05.daos.CandidateDao;
import assign05.entities.Candidate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        try {
            String idParam = req.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                showError(out, "Invalid candidate ID");
                return;
            }
            
            int candidateId = Integer.parseInt(idParam);
            CandidateDao candidateDao = new CandidateDao();
            Candidate candidate = candidateDao.findById(candidateId);
            
            if (candidate == null) {
                showError(out, "Candidate not found");
                return;
            }
            
            // Display candidate details in HTML form controls
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Edit Candidate - Online Voting System</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; max-width: 600px; margin: 50px auto; padding: 20px; background-color: #f5f5f5; }");
            out.println(".container { background-color: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
            out.println("h2 { text-align: center; color: #333; margin-bottom: 30px; }");
            out.println(".form-group { margin-bottom: 15px; }");
            out.println("label { display: block; margin-bottom: 5px; font-weight: bold; color: #555; }");
            out.println("input[type='text'], input[type='number'] { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 5px; font-size: 16px; box-sizing: border-box; }");
            out.println(".readonly { background-color: #f8f9fa; color: #6c757d; }");
            out.println(".btn { background-color: #007bff; color: white; padding: 12px 30px; text-decoration: none; border-radius: 5px; margin: 5px; display: inline-block; }");
            out.println(".btn:hover { background-color: #0056b3; }");
            out.println(".btn-secondary { background-color: #6c757d; }");
            out.println(".btn-secondary:hover { background-color: #545b62; }");
            out.println(".actions { text-align: center; margin-top: 30px; }");
            out.println(".note { background-color: #d1ecf1; border: 1px solid #bee5eb; border-radius: 5px; padding: 15px; margin-top: 20px; color: #0c5460; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h2>Edit Candidate Details</h2>");
            
            // Form to display candidate details (read-only as per assignment requirement)
            out.println("<form>");
            out.println("<div class='form-group'>");
            out.println("<label for='id'>Candidate ID:</label>");
            out.println("<input type='number' id='id' name='id' value='" + candidate.getId() + "' class='readonly' readonly>");
            out.println("</div>");
            
            out.println("<div class='form-group'>");
            out.println("<label for='name'>Candidate Name:</label>");
            out.println("<input type='text' id='name' name='name' value='" + candidate.getName() + "' class='readonly' readonly>");
            out.println("</div>");
            
            out.println("<div class='form-group'>");
            out.println("<label for='party'>Party Name:</label>");
            out.println("<input type='text' id='party' name='party' value='" + candidate.getParty() + "' class='readonly' readonly>");
            out.println("</div>");
            
            out.println("<div class='form-group'>");
            out.println("<label for='votes'>Total Votes:</label>");
            out.println("<input type='number' id='votes' name='votes' value='" + candidate.getVotes() + "' class='readonly' readonly>");
            out.println("</div>");
            out.println("</form>");
            
            out.println("<div class='note'>");
            out.println("<strong>Note:</strong> This is a display-only view as per Assignment 3 requirements. Update functionality is not implemented.");
            out.println("</div>");
            
            out.println("<div class='actions'>");
            out.println("<a href='result' class='btn'>Back to Results</a>");
            out.println("<a href='index.html' class='btn btn-secondary'>Home</a>");
            out.println("</div>");
            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
        } catch (NumberFormatException e) {
            showError(out, "Invalid candidate ID format");
        } catch (SQLException e) {
            e.printStackTrace();
            showError(out, "Database error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            showError(out, "An error occurred: " + e.getMessage());
        }
    }
    
    private void showError(PrintWriter out, String errorMessage) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Error - Online Voting System</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; max-width: 600px; margin: 50px auto; padding: 20px; background-color: #f5f5f5; }");
        out.println(".container { background-color: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); text-align: center; }");
        out.println("h2 { color: #dc3545; margin-bottom: 20px; }");
        out.println("p { color: #666; margin-bottom: 30px; font-size: 16px; }");
        out.println("a { background-color: #007bff; color: white; padding: 12px 30px; text-decoration: none; border-radius: 5px; display: inline-block; }");
        out.println("a:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h2>Error</h2>");
        out.println("<p>" + errorMessage + "</p>");
        out.println("<a href='result'>Back to Results</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}

