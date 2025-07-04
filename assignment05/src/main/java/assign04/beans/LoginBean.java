package assign05.beans;

import java.sql.SQLException;

import assign05.daos.UserDao;
import assign05.entities.User;

public class LoginBean {
    private String email;
    private String password;
    private String message;
    private boolean loginSuccess;
    private User user;
    
    public LoginBean() {
        this.loginSuccess = false;
        this.message = "";
    }
    
    // Method to authenticate user
    public void authenticate() {
        try {
            if (email == null || email.trim().isEmpty() || 
                password == null || password.trim().isEmpty()) {
                this.message = "Login Failed - Email and password are required";
                this.loginSuccess = false;
                return;
            }
            
            UserDao userDao = new UserDao();
            this.user = userDao.findByEmailAndPassword(email.trim(), password);
            
            if (this.user != null) {
                this.message = "Login Success - Welcome " + this.user.getFirstName() + "!";
                this.loginSuccess = true;
            } else {
                this.message = "Login Failed - Invalid email or password";
                this.loginSuccess = false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            this.message = "Login Failed - Database error: " + e.getMessage();
            this.loginSuccess = false;
        } catch (Exception e) {
            e.printStackTrace();
            this.message = "Login Failed - System error: " + e.getMessage();
            this.loginSuccess = false;
        }
    }
    
    // Getters and Setters
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean isLoginSuccess() {
        return loginSuccess;
    }
    
    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
}

