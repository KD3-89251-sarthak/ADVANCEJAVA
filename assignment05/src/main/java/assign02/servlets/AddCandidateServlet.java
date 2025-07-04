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

@WebServlet("/addcandidate")
public class AddCandidateServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        try {
            // Get form parameters
            String name = req.getParameter("name");
            String party = req.getParameter("party");
            
            // Create Candidate object
            Candidate candidate = new Candidate(name, party);
            
            // Save candidate to database
            CandidateDao candidateDao = new CandidateDao();
            int result = candidateDao.save(candidate);
            
            if (result > 0) {
                // Redirect to result servlet
                resp.sendRedirect("result");
            } else {
                showMessage(out, "Add Candidate Failed", 
                           "Failed to add candidate. Please try again.", 
                           "newcandidate.html", "Try Again");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage(out, "Database Error", 
                       "Database error occurred: " + e.getMessage(), 
                       "newcandidate.html", "Try Again");
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(out, "Error", 
                       "An error occurred: " + e.getMessage(), 
                       "newcandidate.html", "Try Again");
        }
    }
    
    private void showMessage(PrintWriter out, String title, String message, 
                           String redirectUrl, String linkText) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>" + title + "</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; max-width: 600px; margin: 50px auto; padding: 20px; background-color: #f5f5f5; }");
        out.println(".container { background-color: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); text-align: center; }");
        out.println("h2 { color: #333; margin-bottom: 20px; }");
        out.println("p { color: #666; margin-bottom: 30px; font-size: 16px; }");
        out.println("a { background-color: #007bff; color: white; padding: 12px 30px; text-decoration: none; border-radius: 5px; display: inline-block; }");
        out.println("a:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h2>" + title + "</h2>");
        out.println("<p>" + message + "</p>");
        out.println("<a href='" + redirectUrl + "'>" + linkText + "</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}

