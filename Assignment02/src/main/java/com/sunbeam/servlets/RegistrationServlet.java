package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")

public class RegistrationServlet extends HttpServlet {	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String fname = req.getParameter("first_name");
		String lname = req.getParameter("last_name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String birth = req.getParameter("dob");
		Date dt = Date.valueOf(birth);
		
		
		User u = new User(0,fname, lname, email, password, dt,false, "voter");
		
		try(UserDao userDao = new UserDaoImpl()){
			
			int count = userDao.save(u);
			if(count > 0) {
				resp.sendRedirect("index.html");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
		
	
		
	}
	
	
	

}
