package com.codingchallenge.hrms.employee.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepository {
	
	public List<Map<String,String>> getEmployeeProfile(Long empId){
		
 		Connection con = null;
		List<Map<String,String>> employeeProfile = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hrms";
			con = DriverManager.getConnection(url,"root","123456789");
			String employee = "SELECT * FROM employee  WHERE empId = ?";				
			PreparedStatement stmt = con.prepareStatement(employee);
			stmt.setLong(1,empId);
			ResultSet rs = stmt.executeQuery();
			
			employeeProfile = new ArrayList<Map<String,String>>();
			System.out.println("before while loop" );
			while(rs.next()) {
				System.out.println("after select employe:" + rs.getString("firstName") );
				Map<String,String> row = new HashMap<String,String>();
				row.put("empId",rs.getString("empId"));
				row.put("firstName",rs.getString("firstName"));
				row.put("lastName",rs.getString("lastName"));
				row.put("email",rs.getString("email"));
				row.put("phone",rs.getString("phone"));
				row.put("image",rs.getString("image"));
				row.put("gender",rs.getString("gender"));
				row.put("dateOfJoining",rs.getString("dateOfJoining"));
				row.put("dateOfBirth",rs.getString("dateOfBirth"));
				row.put("jobTitle",rs.getString("jobTitle"));
				row.put("department",rs.getString("department"));
				row.put("address",rs.getString("address"));
				employeeProfile.add(row);
			}
			con.close();
			System.out.println("after" + employeeProfile.size());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return employeeProfile;		
	}
	
	public Map<String, String> getEmployeeDetails(Long empId) {
		System.out.println("sffdfsdf");
		Connection con = null;
		Map<String, String> showemployee = null;
		System.out.println("before try");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hrms";
			con = DriverManager.getConnection(url,"root","123456789");
			System.out.println("inside database");
			String employee = "SELECT * FROM employee WHERE empId =?";
			PreparedStatement stmt = con.prepareStatement(employee);
			stmt.setLong(1, empId);
			ResultSet rs = stmt.executeQuery();
			showemployee = new HashMap<String, String>();
			System.out.println("before while");
			while (rs.next()) {
				showemployee.put("firstName", rs.getString("firstName"));
				showemployee.put("lastName", rs.getString("lastName"));
				showemployee.put("empId", rs.getString("empId"));
				showemployee.put("email", rs.getString("email"));
				showemployee.put("phone", rs.getString("phone"));
				showemployee.put("dateOfBirth", rs.getString("dateOfBirth"));
				showemployee.put("dateOfJoining", rs.getString("dateOfJoining"));
				showemployee.put("address", rs.getString("address"));
				showemployee.put("gender", rs.getString("gender"));
				showemployee.put("jobTitle", rs.getString("jobTitle"));
				showemployee.put("department", rs.getString("department"));
				showemployee.put("image", rs.getString("image"));

			}
			System.out.println("after while" + showemployee);
			con.close();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return showemployee;
	}

	public void updateEmployee(Long empId, String firstName, String lastName, String email, Long phone,
			String dateOfBirth, String dateOfJoining, String address, String gender, String jobTitle,
			String department) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hrms";
			con = DriverManager.getConnection(url,"root","123456789");
			// current date into Created On.
					//	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
						//Date modified_date = new Date();
						//String modifiedOn = dateFormat.format(modified_date);
						//System.out.println(modifiedOn);
						
			String updateQuery = "UPDATE employee SET firstName=?,lastName=?,email=?,phone=?, dateOfBirth=?,dateOfJoining=?,address=?,gender=?,jobTitle=?,department=? WHERE empId=?";
			
			PreparedStatement stmt = con.prepareStatement(updateQuery);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, email);
			stmt.setLong(4, phone);
			stmt.setString(5, dateOfBirth);
			stmt.setString(6, dateOfJoining);
			stmt.setString(7, address);
			stmt.setString(8, gender);
			stmt.setString(9, jobTitle);
			stmt.setString(10, department);
			stmt.setLong(11, empId);
			//stmt.setString(12, modifiedOn);


			boolean updated = stmt.execute();

			if (updated) {
				System.out.println("success");
			} else {

				System.out.println("failed");
			}
			con.close();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public void deleteEmployee(Long empId) {
		System.out.println("sffdfsdf");
		Connection con = null;
		Map<String, String> delete = null;
		System.out.println("before try");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hrms";
			con = DriverManager.getConnection(url,"root","123456789");
			System.out.println("inside database");
			String employee = "DELETE FROM  employee WHERE empId=?";

			PreparedStatement stmt = con.prepareStatement(employee);
			stmt.setLong(1, empId);
			System.out.println("before while");
			boolean deleted = stmt.execute();

			if (deleted) {
				System.out.println("success delete");
			} else {

				System.out.println("failed delete");
			}
			con.close();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
	public Boolean saveEmployee(Long empId,String firstName, String lastName,String email, Long phone,String dateOfBirth,String dateOfJoining,String address,String gender,String jobTitle,String department,String image) {
		Connection con = null;
		boolean successfull = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hrms";
			con = DriverManager.getConnection(url,"root","123456789");
			String insertEmployee = "INSERT INTO employee(empId,firstName,lastName,email,phone,dateOfBirth,dateOfJoining,address,gender,jobTitle,department,image)"
					+  "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(insertEmployee);

			stmt.setLong(1, empId);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, email);
			stmt.setLong(5, phone);
			stmt.setString(6, dateOfBirth);
			stmt.setString(7, dateOfJoining);
			stmt.setString(8, address);
			stmt.setString(9, gender);
			stmt.setString(10, jobTitle);
			stmt.setString(11, department);
			stmt.setString(12, image);
			Boolean inserted = stmt.execute();
			System.out.println(inserted);
			if (stmt.getUpdateCount() > 0) {
				System.out.println("Data Inserted Successful");

			} else {
				System.out.println("Data Inserted failed");

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return successfull;
	}
	public List<Map<String,String>> getListEmployee(){
 		Connection con = null;
		List<Map<String,String>> ListEmployee = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hrms";
			con = DriverManager.getConnection(url,"root","123456789");
			String employee = "SELECT * FROM employee ";				
			PreparedStatement stmt = con.prepareStatement(employee);
			
			ResultSet rs = stmt.executeQuery();
			
			ListEmployee = new ArrayList<Map<String,String>>();
			System.out.println("before while loop" );
			while(rs.next()) {
//				System.out.println(rs.getString("empId"));
//				System.out.println(rs.getString("firstName")+rs.getString("lastName"));
//				System.out.println(rs.getString("department"));
				Map<String,String> row = new HashMap<String,String>();
				row.put("empId",rs.getString("empId"));
				row.put("firstName",rs.getString("firstName"));
				row.put("lastName",rs.getString("lastName"));
				row.put("department",rs.getString("department"));
				row.put("jobTitle",rs.getString("jobTitle"));
				row.put("image",rs.getString("image"));
				ListEmployee.add(row);
			}
			con.close();
			System.out.println("after" + ListEmployee.size());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ListEmployee;		
	}
	public Map<String,String> getViewEmployeeAdmin( Long empId){
 		Connection con = null;
		Map<String,String> row = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hrms";
			con = DriverManager.getConnection(url,"root","123456789");
			String viewemployee = "SELECT * FROM employee WHERE empId=? ";				
			PreparedStatement stmt = con.prepareStatement(viewemployee);
			stmt.setLong(1, empId);
			ResultSet rs = stmt.executeQuery();
			
			row = new HashMap<String,String>();
			System.out.println("before while loop" );
			while(rs.next()) {
				
				
				row.put("empId",rs.getString("empId"));
				row.put("firstName",rs.getString("firstName"));
				row.put("lastName",rs.getString("lastName"));
				row.put("email",rs.getString("email"));
				row.put("phone",rs.getString("phone"));
				row.put("dateOfBirth",rs.getString("dateOfBirth"));
				row.put("dateOfJoining",rs.getString("dateOfJoining"));
				row.put("address",rs.getString("address"));
				row.put("gender",rs.getString("gender"));
				row.put("jobTitle",rs.getString("jobTitle"));
				row.put("department",rs.getString("department"));
				row.put("image",rs.getString("image"));
				
		}
			con.close();
			System.out.println("after" + row.size());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return row;
		
	}

	public boolean findEmployee(Long empId) {
		System.out.println("emp 1");
		System.out.println("empid : "+empId);
		Connection con = null;
		boolean successful = false;
		try {
			System.out.println("emp 2");

			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hrms";
			con = DriverManager.getConnection(url,"root","123456789");
			System.out.println("emp 3");

			String employee = "SELECT * FROM employee  WHERE empId = ?";
			System.out.println("emp 4");

			PreparedStatement stmt = con.prepareStatement(employee);
			System.out.println("emp 5");

			stmt.setLong(1,empId);
			System.out.println("emp 6");

			ResultSet rs = stmt.executeQuery();
			System.out.println("emp 7");

			while(rs.next()) {
//				System.out.println(rs.getString("userName"));
				if(rs.getString("empId") != null) {
					successful = true;
//					System.out.println("UseName" + rs.getString("userName"));
				}				
			}
			if(successful == true)  {
				System.out.println("There is a user");
			}
			else {
				System.out.println("No user found.");
				successful = false;
			}			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return successful;		
		
	}

}
