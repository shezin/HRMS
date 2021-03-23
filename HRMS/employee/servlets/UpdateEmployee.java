package com.codingchallenge.hrms.employee.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codingchallenge.hrms.employee.repositories.EmployeeRepository;
import com.codingchallenge.hrms.util.AuthUtil;

/**
 * Servlet implementation class UpdateEmployee
 */
@WebServlet("/UpdateEmployee")
public class UpdateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     *
     */
	

    public UpdateEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher serve = null;				
		if(AuthUtil.isAuthenticated(request, response)) {
			Long empId = Long.valueOf(request.getParameter("empId"));
			serve = request.getRequestDispatcher("update_employee.jsp");
			EmployeeRepository empobj = new EmployeeRepository();		
			Map<String, String> showEmployee = empobj.getEmployeeDetails(empId);		
			request.setAttribute("showemployee", showEmployee);
		}else {
			serve = request.getRequestDispatcher("access_denied.jsp");
		}	
			serve.forward(request, response); 
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("firstName"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		Long empId = Long.valueOf(request.getParameter("empId"));
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		Long phone = Long.valueOf(request.getParameter("phone"));
		String dateOfJoining = request.getParameter("dateOfJoining");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String jobTitle = request.getParameter("jobTitle");
		String department = request.getParameter("department");
		String address = request.getParameter("address");
		if ((firstName != null && firstName.isEmpty()) && (empId != null) && (phone != null)
				&& (email != null && email.isEmpty())) {
			System.out.println("validation failed");
			request.setAttribute("validation failed", true);
			;
		} else {
			System.out.println("successful");

			EmployeeRepository empRepoObj = new EmployeeRepository();
			empRepoObj.updateEmployee(empId, firstName, lastName, email, phone, dateOfBirth, dateOfJoining, address,
					gender, jobTitle, department);
			response.sendRedirect("ListEmployee");
//			doGet(request, response);
		}

	}
}
