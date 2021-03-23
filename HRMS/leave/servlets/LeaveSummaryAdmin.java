package com.codingchallenge.hrms.leave.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codingchallenge.hrms.leave.repositories.LeaveRepository;
import com.codingchallenge.hrms.util.AuthUtil;

//import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

/**
 * Servlet implementation class LeaveSummaryAdmin
 */
@WebServlet("/LeaveSummaryAdmin")
public class LeaveSummaryAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LeaveSummaryAdmin() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher serve = null;				
		if(AuthUtil.isAuthenticated(request, response)) {
			serve = request.getRequestDispatcher("leave_summary_admin.jsp");
			LeaveRepository summaryobj = new LeaveRepository();
			System.out.println("kvbskjvbi");
			List<Map<String, String>> leaveSummary = summaryobj.getAllApplicationsByAdmin();
			System.out.println("list size" + leaveSummary.size());
			request.setAttribute("leavesummary", leaveSummary);
			
		}else {
			serve = request.getRequestDispatcher("access_denied.jsp");
		}	
			serve.forward(request, response); 
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		


}
