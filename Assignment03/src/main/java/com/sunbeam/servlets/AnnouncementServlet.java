package com.sunbeam.servlets;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/announce")
public class AnnouncementServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		processRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
		
		
		
	}
	          
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String ann = req.getParameter("ann");
		
		ServletContext ctx = req.getServletContext();
		ctx.setAttribute("annmsg", ann);
		
		
		resp.sendRedirect("result");
		
		
		
	}

}
