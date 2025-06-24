package com.sunbeam.servlets;

import java.io.IOException;

import com.sunbeam.daos.CandidateDao;
import com.sunbeam.daos.CandidateDaoImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/delcand")
public class DeleteCandidateServlet extends HttpServlet {
	
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
			int count = candDao.deleteById(id);
			System.out.println("Candidates Deleted : " + count);
			
			String msg = "Candidates Deleted : " + count;
			req.setAttribute("msg", msg);
			
			RequestDispatcher rd = req.getRequestDispatcher("result");
			rd.forward(req,resp);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
