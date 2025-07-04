package assign05.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;

import assign05.daos.UserDao;
import assign05.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        try {
            // Get form parameters
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String dobStr = req.getParameter("dob");
            String mobile = req.getParameter("mobile");
            
            // Convert date string to LocalDate
            LocalDate dob = LocalDate.parse(dobStr);
            
            // Create User object
            User user = new User(firstName, lastName, email, password, dob, mobile);
            
            // Save user to database
            UserDao userDao = new UserDao();
            
            // Check if user already exists
            User existingUser = userDao.findByEmail(email);
            if (existingUser != null) {
                showMessage(out, "Registration Failed", 
                           "User with email " + email + " already exists!", 
                           "register.html", "Try Again");
                return;
            }
            
            int result = userDao.save(user);
            
            if (result > 0) {
                showMessage(out, "Registration Successful", 
                           "User registered successfully! You can now login.", 
                           "index.html", "Go to Login");
            } else {
                showMessage(out, "Registration Failed", 
                           "Failed to register user. Please try again.", 
                           "register.html", "Try Again");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage(out, "Database Error", 
                       "Database error occurred: " + e.getMessage(), 
                       "register.html", "Try Again");
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(out, "Error", 
                       "An error occurred: " + e.getMessage(), 
                       "register.html", "Try Again");
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

