package com.sunbeam.servlets;

import java.io.IOException;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;
import com.sunbeam.pojos.Candidate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/candidate")
public class CandidateServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String party = req.getParameter("party");
		String votes = req.getParameter("votes");
		int vote = Integer.parseInt(votes);
		
		Candidate cand = new Candidate(0,name,party,vote);
		try(CandidateDao candDao = new CandidateDaoImpl()){
			
			int count = candDao.save(cand);
			if(count > 0) {
				resp.sendRedirect("result");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
				
		
	
	}

}
