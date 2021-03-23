package com.codingchallenge.hrms.employee.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codingchallenge.hrms.employee.repositories.EmployeeRepository;
import com.codingchallenge.hrms.util.AuthUtil;

/**
 * Servlet implementation class DeleteEmployee
 */
@WebServlet("/DeleteEmployee")
public class DeleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher serve = null;				
		if(AuthUtil.isAuthenticated(request, response)) {				
			if (null != (request.getParameter("empId"))) {				
				EmployeeRepository deleteobj = new EmployeeRepository();
				Long empId = Long.valueOf(request.getParameter("empId"));
				deleteobj.deleteEmployee(empId);
				response.sendRedirect("ListEmployee");
			}
			}else {
				serve = request.getRequestDispatcher("access_denied.jsp");
				serve.forward(request, response); 
			}	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
