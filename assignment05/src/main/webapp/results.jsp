<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="assign05.entities.User, assign05.entities.Candidate" %>
<%@ page import="assign05.daos.CandidateDao, assign05.daos.VoteDao" %>
<%@ page import="java.util.List" %>
<%
    // Check if user is logged in
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    
    // Get all candidates and total votes
    CandidateDao candidateDao = new CandidateDao();
    List<Candidate> candidates = candidateDao.findAll();
    
    VoteDao voteDao = new VoteDao();
    int totalVotes = voteDao.getTotalVotes();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Election Results - Online Voting System</title>
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
        .stats {
            background-color: #e7f3ff;
            border: 1px solid #b3d9ff;
            border-radius: 5px;
            padding: 15px;
            margin-bottom: 30px;
            text-align: center;
        }
        .stats h3 {
            margin: 0;
            color: #004085;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 30px;
        }
        th, td {
            padding: 15px;
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
        .rank {
            font-weight: bold;
            font-size: 1.2em;
        }
        .rank.first {
            color: #ffd700;
        }
        .rank.second {
            color: #c0c0c0;
        }
        .rank.third {
            color: #cd7f32;
        }
        .vote-bar {
            background-color: #e9ecef;
            border-radius: 10px;
            height: 20px;
            margin: 5px 0;
            overflow: hidden;
        }
        .vote-fill {
            background-color: #007bff;
            height: 100%;
            border-radius: 10px;
            transition: width 0.3s ease;
        }
        .percentage {
            font-weight: bold;
            color: #007bff;
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
    </style>
</head>
<body>
    <div class="header">
        <div>
            <h2 style="margin: 0; color: #007bff;">Election Results</h2>
            <p style="margin: 5px 0 0 0; color: #666;">Real-time voting results</p>
        </div>
        <a href="dashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
    </div>
    
    <div class="container">
        <h1>Election 2024 - Live Results</h1>
        
        <div class="stats">
            <h3>Total Votes Cast: <%= totalVotes %></h3>
        </div>
        
        <% if (candidates.isEmpty()) { %>
            <p style="text-align: center; color: #666; font-size: 1.2em;">No candidates found.</p>
        <% } else { %>
            <table>
                <tr>
                    <th>Rank</th>
                    <th>Candidate</th>
                    <th>Party</th>
                    <th>Votes</th>
                    <th>Percentage</th>
                    <th>Vote Distribution</th>
                    <% if ("admin".equals(user.getRole())) { %>
                        <th>Actions</th>
                    <% } %>
                </tr>
                
                <% 
                int rank = 1;
                int maxVotes = candidates.isEmpty() ? 1 : candidates.get(0).getVotes();
                if (maxVotes == 0) maxVotes = 1; // Prevent division by zero
                
                for (Candidate candidate : candidates) { 
                    double percentage = totalVotes > 0 ? (double) candidate.getVotes() / totalVotes * 100 : 0;
                    double barWidth = maxVotes > 0 ? (double) candidate.getVotes() / maxVotes * 100 : 0;
                    
                    String rankClass = "";
                    if (rank == 1) rankClass = "first";
                    else if (rank == 2) rankClass = "second";
                    else if (rank == 3) rankClass = "third";
                %>
                    <tr>
                        <td class="rank <%= rankClass %>"><%= rank %></td>
                        <td><strong><%= candidate.getName() %></strong></td>
                        <td><%= candidate.getParty() %></td>
                        <td><strong><%= candidate.getVotes() %></strong></td>
                        <td class="percentage"><%= String.format("%.1f", percentage) %>%</td>
                        <td>
                            <div class="vote-bar">
                                <div class="vote-fill" style="width: <%= barWidth %>%"></div>
                            </div>
                        </td>
                        <% if ("admin".equals(user.getRole())) { %>
                            <td>
                                <a href="editcandidate.jsp?id=<%= candidate.getId() %>" class="btn">Edit</a>
                            </td>
                        <% } %>
                    </tr>
                <% 
                    rank++;
                } %>
            </table>
        <% } %>
        
        <div class="actions">
            <% if ("admin".equals(user.getRole())) { %>
                <a href="addcandidate.jsp" class="btn btn-success">Add New Candidate</a>
            <% } %>
            <a href="vote.jsp" class="btn">Cast Vote</a>
            <a href="dashboard.jsp" class="btn btn-secondary">Dashboard</a>
        </div>
    </div>
</body>
</html>

