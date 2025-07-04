<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="assign05.entities.User" %>
<%@ page import="assign05.daos.CandidateDao" %>
<%@ page import="assign05.entities.Candidate" %>
<%
    // Check if user is logged in and is admin
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    
    if (!"admin".equals(user.getRole())) {
        response.sendRedirect("dashboard.jsp");
        return;
    }
    
    String message = "";
    boolean showForm = true;
    
    // Handle form submission
    if ("POST".equals(request.getMethod())) {
        String name = request.getParameter("name");
        String party = request.getParameter("party");
        
        if (name != null && !name.trim().isEmpty() && 
            party != null && !party.trim().isEmpty()) {
            
            try {
                Candidate candidate = new Candidate(name.trim(), party.trim());
                CandidateDao candidateDao = new CandidateDao();
                int result = candidateDao.save(candidate);
                
                if (result > 0) {
                    message = "Candidate '" + name + "' has been successfully added!";
                    showForm = false;
                } else {
                    message = "Failed to add candidate. Please try again.";
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                message = "Error occurred: " + e.getMessage();
            }
        } else {
            message = "Please fill in all required fields.";
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Candidate - Online Voting System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .header {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            margin-bottom: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #28a745;
            color: white;
            padding: 12px 30px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
            margin-top: 20px;
        }
        input[type="submit"]:hover {
            background-color: #218838;
        }
        .message {
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center;
        }
        .success {
            background-color: #d4edda;
            border: 1px solid #c3e6cb;
            color: #155724;
        }
        .error {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            color: #721c24;
        }
        .btn {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            margin: 5px;
            display: inline-block;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .btn-secondary {
            background-color: #6c757d;
        }
        .btn-secondary:hover {
            background-color: #545b62;
        }
        .actions {
            text-align: center;
            margin-top: 20px;
        }
        .admin-note {
            background-color: #e7f3ff;
            border: 1px solid #b3d9ff;
            border-radius: 5px;
            padding: 15px;
            margin-bottom: 20px;
            color: #004085;
        }
    </style>
</head>
<body>
    <div class="header">
        <div>
            <h2 style="margin: 0; color: #007bff;">Add New Candidate</h2>
            <p style="margin: 5px 0 0 0; color: #666;">Admin Panel</p>
        </div>
        <a href="dashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
    </div>
    
    <div class="container">
        <h1>Add New Candidate</h1>
        
        <div class="admin-note">
            <strong>Admin Access:</strong> You are adding a new candidate to the election.
        </div>
        
        <% if (!message.isEmpty()) { %>
            <div class="message <%= showForm ? "error" : "success" %>">
                <%= message %>
            </div>
        <% } %>
        
        <% if (showForm) { %>
            <form method="post">
                <div class="form-group">
                    <label for="name">Candidate Name:</label>
                    <input type="text" id="name" name="name" required 
                           placeholder="Enter candidate's full name">
                </div>
                
                <div class="form-group">
                    <label for="party">Party Name:</label>
                    <input type="text" id="party" name="party" required 
                           placeholder="Enter party name">
                </div>
                
                <input type="submit" value="Add Candidate">
            </form>
        <% } %>
        
        <div class="actions">
            <% if (!showForm) { %>
                <a href="addcandidate.jsp" class="btn">Add Another Candidate</a>
                <a href="results.jsp" class="btn">View Results</a>
            <% } %>
            <a href="managecandidates.jsp" class="btn">Manage Candidates</a>
            <a href="dashboard.jsp" class="btn btn-secondary">Dashboard</a>
        </div>
    </div>
</body>
</html>

