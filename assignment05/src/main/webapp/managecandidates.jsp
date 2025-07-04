<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="assign05.entities.User, assign05.entities.Candidate" %>
<%@ page import="assign05.daos.CandidateDao" %>
<%@ page import="java.util.List" %>
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
    
    // Get all candidates
    CandidateDao candidateDao = new CandidateDao();
    List<Candidate> candidates = candidateDao.findAll();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Candidates - Online Voting System</title>
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 30px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .btn {
            background-color: #007bff;
            color: white;
            padding: 8px 15px;
            text-decoration: none;
            border-radius: 5px;
            margin: 2px;
            display: inline-block;
            font-size: 14px;
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
        .no-candidates {
            text-align: center;
            color: #666;
            font-size: 1.2em;
            padding: 40px;
        }
    </style>
</head>
<body>
    <div class="header">
        <div>
            <h2 style="margin: 0; color: #007bff;">Manage Candidates</h2>
            <p style="margin: 5px 0 0 0; color: #666;">Admin Panel</p>
        </div>
        <a href="dashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
    </div>
    
    <div class="container">
        <h1>Candidate Management</h1>
        
        <div class="admin-note">
            <strong>Admin Access:</strong> Manage all candidates in the election system. You can view and edit candidate details.
        </div>
        
        <% if (candidates.isEmpty()) { %>
            <div class="no-candidates">
                <p>No candidates found in the system.</p>
                <a href="addcandidate.jsp" class="btn btn-success">Add First Candidate</a>
            </div>
        <% } else { %>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Candidate Name</th>
                    <th>Party</th>
                    <th>Total Votes</th>
                    <th>Actions</th>
                </tr>
                
                <% for (Candidate candidate : candidates) { %>
                    <tr>
                        <td><%= candidate.getId() %></td>
                        <td><strong><%= candidate.getName() %></strong></td>
                        <td><%= candidate.getParty() %></td>
                        <td><%= candidate.getVotes() %></td>
                        <td>
                            <a href="editcandidate.jsp?id=<%= candidate.getId() %>" class="btn">Edit</a>
                        </td>
                    </tr>
                <% } %>
            </table>
        <% } %>
        
        <div class="actions">
            <a href="addcandidate.jsp" class="btn btn-success">Add New Candidate</a>
            <a href="results.jsp" class="btn">View Results</a>
            <a href="dashboard.jsp" class="btn btn-secondary">Dashboard</a>
        </div>
    </div>
</body>
</html>

