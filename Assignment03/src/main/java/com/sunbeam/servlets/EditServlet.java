package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.pojos.Candidate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit")

public class EditServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String candId = req.getParameter("candid");
		int id = Integer.parseInt(candId);
		
		try(CandidateDao candDao = new CandidateDaoImpl()){
			
			Candidate c =candDao.findById(id);
			
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Edit Candidate</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Edit Candidate</h1>");
			
			
			
			out.println("<form>");


			out.println("<div> ");
			out.printf("Id: <input readonly disabled type='id' name='id' value='%d' /> ",c.getId());
			out.println("</div>");

			out.println("<div> ");
			out.printf("Name: <input type='text' name='name' value='%s' /> ",c.getName());
			out.println("</div>");
			
			out.println("<div> ");
			out.printf("Party: <input type='text' name='party' value='%s' /> " , c.getParty());
			out.println("</div>");

			out.println("<div> ");
			out.printf("Votes: <input readonly disabled type='number' name='party' value='%d' /> " , c.getVotes());
			out.println("</div>");
			
			
			out.println("<div> ");
			out.printf("<input type='submit' value='Update' />");
			out.println("</div>");

			out.println("</form>");
			
	
			
			
			
			out.println("</body>");
			out.println("</html>");
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	
		
		
	}
	
	
	
	

}
