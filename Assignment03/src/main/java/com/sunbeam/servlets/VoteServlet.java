package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);    
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
		
	}
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException {
		
		HttpSession session = req.getSession();
		User u = (User)session.getAttribute("user");
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		
		if(u.isStatus() == true) {
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Vote</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h2>Already Voted</h2>");
			out.println("<a href='logout'>Log Out</a>");
			out.println("</body>");
			out.println("</html>");
			return;			
			
			
		}
		
		
		u.setStatus(true);
		try(UserDao userDao = new UserDaoImpl()){
			int count = userDao.update(u);
			System.out.println("User Status Updated" + count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String candId = req.getParameter("candidate");
		int id = Integer.parseInt(candId);
		
		try(CandidateDao candDao = new CandidateDaoImpl()){
			int count = candDao.incrVote(id);
			System.out.println("Candidate Vites Incremented" + count);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Vote</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2> Congratulations ! </h2>");
		out.println("Your vote registered Succesfully <br/> </br>");
		out.println("<a href='logout' >SignOut</a>");
		out.println("</body>");
		out.println("</html>");
		
	}
	

}
