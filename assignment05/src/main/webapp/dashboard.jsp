<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="assign05.entities.User" %>
<%
    // Check if user is logged in
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Online Voting System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 900px;
            margin: 20px auto;
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
        .user-info {
            color: #333;
        }
        .user-info h2 {
            margin: 0;
            color: #007bff;
        }
        .logout-btn {
            background-color: #dc3545;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
        }
        .logout-btn:hover {
            background-color: #c82333;
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
        .nav-links {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }
        .nav-card {
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
            border-radius: 10px;
            padding: 20px;
            text-align: center;
            transition: transform 0.2s;
        }
        .nav-card:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .nav-card h3 {
            color: #333;
            margin-bottom: 10px;
        }
        .nav-card p {
            color: #666;
            margin-bottom: 15px;
            font-size: 14px;
        }
        .nav-link {
            background-color: #007bff;
            color: white;
            padding: 12px 25px;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
            font-weight: bold;
        }
        .nav-link:hover {
            background-color: #0056b3;
        }
        .nav-link.vote {
            background-color: #28a745;
        }
        .nav-link.vote:hover {
            background-color: #218838;
        }
        .nav-link.admin {
            background-color: #17a2b8;
        }
        .nav-link.admin:hover {
            background-color: #138496;
        }
        .nav-link.results {
            background-color: #ffc107;
            color: #212529;
        }
        .nav-link.results:hover {
            background-color: #e0a800;
        }
        .role-badge {
            background-color: #007bff;
            color: white;
            padding: 4px 8px;
            border-radius: 12px;
            font-size: 12px;
            text-transform: uppercase;
        }
        .role-badge.admin {
            background-color: #dc3545;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="user-info">
            <h2>Welcome, <%= user.getFirstName() %> <%= user.getLastName() %>!</h2>
            <p>Email: <%= user.getEmail() %> | Role: <span class="role-badge <%= user.getRole() %>"><%= user.getRole() %></span></p>
        </div>
        <a href="logout.jsp" class="logout-btn">Sign Out</a>
    </div>
    
    <div class="container">
        <h1>Voting System Dashboard</h1>
        
        <div class="nav-links">
            <div class="nav-card">
                <h3>Cast Your Vote</h3>
                <p>Vote for your preferred candidate in the election</p>
                <a href="vote.jsp" class="nav-link vote">Vote Now</a>
            </div>
            
            <div class="nav-card">
                <h3>Election Results</h3>
                <p>View current election results and candidate standings</p>
                <a href="results.jsp" class="nav-link results">View Results</a>
            </div>
            
            <% if ("admin".equals(user.getRole())) { %>
            <div class="nav-card">
                <h3>Add Candidate</h3>
                <p>Add new candidates to the election</p>
                <a href="addcandidate.jsp" class="nav-link admin">Add Candidate</a>
            </div>
            
            <div class="nav-card">
                <h3>Manage Candidates</h3>
                <p>Edit and manage existing candidates</p>
                <a href="managecandidates.jsp" class="nav-link admin">Manage</a>
            </div>
            <% } %>
        </div>
    </div>
</body>
</html>

