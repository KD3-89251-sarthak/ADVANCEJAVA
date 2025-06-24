package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.pojos.Candidate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/result")
public class ResultServlet extends HttpServlet{
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Candidate> list = new ArrayList<>();
		
		try(CandidateDao candDao = new CandidateDaoImpl()){
			list = candDao.findAll();
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Result </title>");
		out.println("</head>");
		out.println("<body>");
		
		
		String userName = "";
		Cookie[] arr = req.getCookies();
		
		if(arr!=null) {
			for(Cookie c : arr) {
				if(c.getName().equals("uname"))
					userName = c.getValue();
					
			}
		}
		
		out.println("Hello, "+ userName + "<hr/>");
		
		
		
		out.println("<table border='1' >");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>");
		out.println("id");
		out.println("</th>");
		
		
		
		out.println("<th>");
		out.println("name");
		out.println("</th>");
		
		out.println("<th>");
		out.println("party");
		out.println("</th>");
		
		out.println("<th>");
		out.println("votes");
		out.println("</th>");	
		
		
		out.println("<th>");
		out.println("Actions");
		out.println("</th>");
		
		
		out.println("</tr>");
		
		out.println("</thead>");
		out.println("<tbody>");
		
		
		for(Candidate cand : list) {
			out.printf("<tr> <td> %d </td> <td> %s </td>  <td> %s </td>  <td> %d </td>     " , cand.getId(),cand.getName(),cand.getParty(),cand.getVotes());
			out.println("<td>");
			out.printf("<a href='delcand?candid=%d' ><img src='images/delete.png' alt='Delete' height='25px'  </a>", cand.getId());
			out.printf("<a href='edit?candid=%d' ><img src='images/edit.png' alt='Edit' height='25px'  </a>", cand.getId());
			out.println("</td></tr>");
		}
		
		
		out.println("</tbody>");
		
		
		
		out.println("</table>");
		out.println("</br>");
		
		String msg = (String)req.getAttribute("msg");
		if(msg!=null) {
			out.println("<p>" + msg + "</p>");
		}
		
		out.println("<br/>");
		
		
		out.println("<a href='newcandidate.html'>Add New Candidate</a>");
		
		out.println("<br/> <a href='announce.html'>Annoucement</a>");
		out.println("<br/> <a href='logout'>Sign Out</a>");
		
		
		out.println("</body>");
		
		out.println("</html>");
		
		
		
		
		
	}
	

}
