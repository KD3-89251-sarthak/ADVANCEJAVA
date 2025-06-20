<%@page import="com.sunbeam.LoginBean" %>
<%@page import="com.sunbeam.pojos.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="lb" class="com.sunbeam.LoginBean"/> 
<jsp:setProperty property="email" name="lb" param="email"/>
<jsp:setProperty property="password" name="lb" param="password"/>
<% lb.validate(); %>
<% if(lb.getUser() != null){ %>
Welcome, <jsp:getProperty name="lb" property="email" />
<% }else{ %>
Failed  
<% } %>

	







</body>
</html>