<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="assign05.entities.User, assign05.entities.Candidate" %>
<%@ page import="assign05.daos.CandidateDao" %>
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
    
    String candidateIdStr = request.getParameter("id");
    Candidate candidate = null;
    String errorMessage = "";
    
    if (candidateIdStr != null && !candidateIdStr.trim().isEmpty()) {
        try {
            int candidateId = Integer.parseInt(candidateIdStr);
            CandidateDao candidateDao = new CandidateDao();
            candidate = candidateDao.findById(candidateId);
            
            if (candidate == null) {
                errorMessage = "Candidate not found.";
            }
            
        } catch (NumberFormatException e) {
            errorMessage = "Invalid candidate ID.";
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage = "Database error: " + e.getMessage();
        }
    } else {
        errorMessage = "Candidate ID is required.";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Candidate - Online Voting System</title>
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
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
            box-sizing: border-box;
        }
        .readonly {
            background-color: #f8f9fa;
            color: #6c757d;
        }
        .error {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            border-radius: 5px;
            padding: 20px;
            margin-bottom: 20px;
            color: #721c24;
            text-align: center;
        }
        .note {
            background-color: #d1ecf1;
            border: 1px solid #bee5eb;
            border-radius: 5px;
            padding: 15px;
            margin-top: 20px;
            color: #0c5460;
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
            margin-top: 30px;
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
            <h2 style="margin: 0; color: #007bff;">Edit Candidate</h2>
            <p style="margin: 5px 0 0 0; color: #666;">Admin Panel - Display Only</p>
        </div>
        <a href="dashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
    </div>
    
    <div class="container">
        <h1>Edit Candidate Details</h1>
        
        <div class="admin-note">
            <strong>Assignment 5 Feature:</strong> This page displays candidate details in form controls as per requirements. Update functionality is not implemented.
        </div>
        
        <% if (!errorMessage.isEmpty()) { %>
            <div class="error">
                <h3>Error</h3>
                <p><%= errorMessage %></p>
            </div>
        <% } else if (candidate != null) { %>
            <form>
                <div class="form-group">
                    <label for="id">Candidate ID:</label>
                    <input type="number" id="id" name="id" value="<%= candidate.getId() %>" 
                           class="readonly" readonly>
                </div>
                
                <div class="form-group">
                    <label for="name">Candidate Name:</label>
                    <input type="text" id="name" name="name" value="<%= candidate.getName() %>" 
                           class="readonly" readonly>
                </div>
                
                <div class="form-group">
                    <label for="party">Party Name:</label>
                    <input type="text" id="party" name="party" value="<%= candidate.getParty() %>" 
                           class="readonly" readonly>
                </div>
                
                <div class="form-group">
                    <label for="votes">Total Votes:</label>
                    <input type="number" id="votes" name="votes" value="<%= candidate.getVotes() %>" 
                           class="readonly" readonly>
                </div>
            </form>
            
            <div class="note">
                <strong>Note:</strong> This is a display-only view as per Assignment 5 requirements. 
                The form shows candidate details in HTML form controls but update functionality is not implemented.
            </div>
        <% } %>
        
        <div class="actions">
            <a href="results.jsp" class="btn">Back to Results</a>
            <a href="managecandidates.jsp" class="btn">Manage Candidates</a>
            <a href="dashboard.jsp" class="btn btn-secondary">Dashboard</a>
        </div>
    </div>
</body>
</html>

