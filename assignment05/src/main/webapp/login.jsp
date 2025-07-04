<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="loginBean" class="assign05.beans.LoginBean" scope="request" />
<jsp:setProperty name="loginBean" property="*" />

<%
    // Authenticate user using LoginBean
    loginBean.authenticate();
    
    // If login is successful, store user info in session
    if (loginBean.isLoginSuccess() && loginBean.getUser() != null) {
        session.setAttribute("user", loginBean.getUser());
        session.setAttribute("email", loginBean.getUser().getEmail());
        session.setAttribute("role", loginBean.getUser().getRole());
        session.setAttribute("userName", loginBean.getUser().getFirstName() + " " + loginBean.getUser().getLastName());
        
        // Redirect to dashboard after successful login
        response.sendRedirect("dashboard.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Result - Online Voting System</title>
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
    </style>
</head>
<body>
    <div class="container">
        <h1>Login Failed</h1>
        
        <div class="message">
            <%= loginBean.getMessage() %>
        </div>
        
        <div>
            <a href="index.jsp" class="btn">Try Again</a>
            <a href="register.html" class="btn btn-success">Register</a>
        </div>
    </div>
</body>
</html>

