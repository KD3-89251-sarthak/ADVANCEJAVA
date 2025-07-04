<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="assign05.entities.User" %>
<%@ page import="assign05.daos.VoteDao" %>
<%
    // Check if user is logged in
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    
    String candidateIdStr = request.getParameter("candidateId");
    boolean voteSuccess = false;
    String message = "";
    
    if (candidateIdStr != null && !candidateIdStr.trim().isEmpty()) {
        try {
            int candidateId = Integer.parseInt(candidateIdStr);
            VoteDao voteDao = new VoteDao();
            
            int result = voteDao.castVote(user.getId(), candidateId);
            
            if (result > 0) {
                voteSuccess = true;
                message = "Your vote has been successfully cast! Thank you for participating in the election.";
            } else {
                message = "You have already voted! Each user can only vote once.";
            }
            
        } catch (NumberFormatException e) {
            message = "Invalid candidate selection. Please try again.";
        } catch (Exception e) {
            e.printStackTrace();
            message = "An error occurred while casting your vote: " + e.getMessage();
        }
    } else {
        message = "Please select a candidate before voting.";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vote Result - Online Voting System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            text-align: center;
        }
        h1 {
            color: #333;
            margin-bottom: 30px;
        }
        .message {
            font-size: 1.2em;
            margin-bottom: 30px;
            padding: 20px;
            border-radius: 5px;
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
            padding: 12px 30px;
            text-decoration: none;
            border-radius: 5px;
            margin: 10px;
            display: inline-block;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .btn-success {
            background-color: #28a745;
        }
        .btn-success:hover {
            background-color: #218838;
        }
        .btn-secondary {
            background-color: #6c757d;
        }
        .btn-secondary:hover {
            background-color: #545b62;
        }
        .vote-icon {
            font-size: 4em;
            margin-bottom: 20px;
        }
        .success .vote-icon {
            color: #28a745;
        }
        .error .vote-icon {
            color: #dc3545;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="<%= voteSuccess ? "success" : "error" %>">
            <div class="vote-icon">
                <%= voteSuccess ? "✓" : "✗" %>
            </div>
            <h1><%= voteSuccess ? "Vote Cast Successfully!" : "Vote Failed" %></h1>
            <div class="message">
                <%= message %>
            </div>
        </div>
        
        <div>
            <% if (voteSuccess) { %>
                <a href="results.jsp" class="btn btn-success">View Results</a>
                <a href="dashboard.jsp" class="btn">Dashboard</a>
            <% } else { %>
                <a href="vote.jsp" class="btn">Try Again</a>
                <a href="dashboard.jsp" class="btn btn-secondary">Dashboard</a>
            <% } %>
        </div>
    </div>
</body>
</html>

