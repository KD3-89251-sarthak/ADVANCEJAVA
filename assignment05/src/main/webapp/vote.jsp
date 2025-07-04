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
    
    // Check if user has already voted
    VoteDao voteDao = new VoteDao();
    boolean hasVoted = voteDao.hasUserVoted(user.getId());
    
    // Get all candidates
    CandidateDao candidateDao = new CandidateDao();
    List<Candidate> candidates = candidateDao.findAll();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cast Your Vote - Online Voting System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
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
        .already-voted {
            background-color: #fff3cd;
            border: 1px solid #ffeaa7;
            border-radius: 5px;
            padding: 20px;
            margin-bottom: 20px;
            color: #856404;
            text-align: center;
        }
        .candidate-list {
            display: grid;
            gap: 15px;
        }
        .candidate-card {
            border: 2px solid #dee2e6;
            border-radius: 10px;
            padding: 20px;
            cursor: pointer;
            transition: all 0.3s;
        }
        .candidate-card:hover {
            border-color: #007bff;
            background-color: #f8f9fa;
        }
        .candidate-card.selected {
            border-color: #28a745;
            background-color: #d4edda;
        }
        .candidate-info {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .candidate-details h3 {
            margin: 0 0 5px 0;
            color: #333;
        }
        .candidate-details p {
            margin: 0;
            color: #666;
        }
        .vote-count {
            background-color: #007bff;
            color: white;
            padding: 8px 15px;
            border-radius: 20px;
            font-weight: bold;
        }
        .vote-btn {
            background-color: #28a745;
            color: white;
            padding: 15px 40px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            width: 100%;
            margin-top: 30px;
        }
        .vote-btn:hover {
            background-color: #218838;
        }
        .vote-btn:disabled {
            background-color: #6c757d;
            cursor: not-allowed;
        }
        .back-btn {
            background-color: #6c757d;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
        }
        .back-btn:hover {
            background-color: #545b62;
        }
        input[type="radio"] {
            margin-right: 10px;
            transform: scale(1.5);
        }
    </style>
</head>
<body>
    <div class="header">
        <div>
            <h2 style="margin: 0; color: #007bff;">Cast Your Vote</h2>
            <p style="margin: 5px 0 0 0; color: #666;">Logged in as: <%= user.getFirstName() %> <%= user.getLastName() %></p>
        </div>
        <a href="dashboard.jsp" class="back-btn">Back to Dashboard</a>
    </div>
    
    <div class="container">
        <h1>Election 2024</h1>
        
        <% if (hasVoted) { %>
            <div class="already-voted">
                <h3>You have already voted!</h3>
                <p>Thank you for participating in the election. You can only vote once.</p>
                <p><a href="results.jsp" style="color: #007bff;">View Current Results</a></p>
            </div>
        <% } else { %>
            <form action="castvote.jsp" method="post">
                <div class="candidate-list">
                    <% for (Candidate candidate : candidates) { %>
                        <label for="candidate_<%= candidate.getId() %>" class="candidate-card">
                            <input type="radio" id="candidate_<%= candidate.getId() %>" 
                                   name="candidateId" value="<%= candidate.getId() %>" required>
                            <div class="candidate-info">
                                <div class="candidate-details">
                                    <h3><%= candidate.getName() %></h3>
                                    <p>Party: <%= candidate.getParty() %></p>
                                </div>
                                <div class="vote-count">
                                    <%= candidate.getVotes() %> votes
                                </div>
                            </div>
                        </label>
                    <% } %>
                </div>
                
                <button type="submit" class="vote-btn">Cast My Vote</button>
            </form>
        <% } %>
    </div>
    
    <script>
        // Add visual feedback for radio button selection
        document.querySelectorAll('input[type="radio"]').forEach(radio => {
            radio.addEventListener('change', function() {
                // Remove selected class from all cards
                document.querySelectorAll('.candidate-card').forEach(card => {
                    card.classList.remove('selected');
                });
                // Add selected class to the parent card
                this.closest('.candidate-card').classList.add('selected');
            });
        });
    </script>
</body>
</html>

