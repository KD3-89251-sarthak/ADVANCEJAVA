package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}
	
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try(UserDao userDao = new UserDaoImpl()){
			
			User dbUser = userDao.findByEmail(email);
			if(dbUser != null && dbUser.getPassword().equals(password)) {
				
				System.out.println("Login Sucessfull");
				if(dbUser.getRole().equals("voter")) {
					resp.sendRedirect("candlist");
				}
				
				
				else if(dbUser.getRole().equals("admin")) {
					resp.sendRedirect("result");
				}
				
				
			}
			
			
			else {
				
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Login</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("Login Failed");
				out.println("<a href='index.html'>Login Again</a>");
				out.println("</body>");			
				
				
				out.println("</html>");
				
				
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
		
		
	}
	
	

}
