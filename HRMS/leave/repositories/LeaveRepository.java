package com.codingchallenge.hrms.leave.repositories;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Date;

import javax.servlet.http.HttpSession;

public class LeaveRepository {
	public Boolean saveLeaveApplication(Long empId,String leaveType, String reason,String date,Long noOfDays,Long status ) {
		System.out.println("inside saveapplication");
//		System.out.println(empId);
		Connection con = null;
		boolean successful = false;
		try {
			System.out.println("inside try");
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hrms";
			con = DriverManager.getConnection(url,"root","123456789");
			System.out.println("inside database");
			
			// current date into Created On.
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
			Date created_date = new Date();
			String createdOn = dateFormat.format(created_date);
			System.out.println(createdOn);
			
			String leaveApplication = "INSERT INTO leave_application(empId,leaveType,date,noOfDays,reason,status,createdOn)"
			           +" VALUES(?,?,?,?,?,?,?)";	
			
			PreparedStatement stmt = con.prepareStatement(leaveApplication);
			stmt.setLong(1,empId);
			stmt.setString(2,leaveType);
			stmt.setString(3,date);
			stmt.setLong(4,noOfDays);
			stmt.setString(5,reason);
			stmt.setLong(6,status);
			stmt.setString(7,createdOn);
			
			
			Boolean inserted = stmt.execute();
			System.out.println(inserted);
			if(stmt.getUpdateCount()>0) {
				System.out.println("Data inserted successfully");
			}
			else {
				System.out.println("data insertion failed");
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
	
 	public List<Map<String,String>> getAllLeaveApplication(long empId){
 		
 		Connection con = null;
		List<Map<String,String>> leaveRequests = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hrms";
			con = DriverManager.getConnection(url,"root","123456789");
			String leaveApplication = "SELECT * FROM leave_application  WHERE empId = ?";				
			PreparedStatement stmt = con.prepareStatement(leaveApplication);
			stmt.setLong(1,empId);
			ResultSet rs = stmt.executeQuery();
			leaveRequests = new ArrayList<Map<String,String>>();
			while(rs.next()) {
				Map<String,String> row = new HashMap<String,String>();
				row.put("leaveType",rs.getString("leaveType"));
				row.put("reason",rs.getString("reason"));
				row.put("date",rs.getString("date"));
				row.put("noOfDays",rs.getString("noOfDays"));
				row.put("status",rs.getString("status"));
				leaveRequests.add(row);
			}
			con.close();
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
		
		return leaveRequests;		
	}
 	
 	public List<Map<String,String>> selectPendingRequest() {
		Connection com = null;
		String selectRequestQuery = "SELECT l.applicationId, l.empId, l.leaveType, l.reason, l.appliedDate, l.noOfDays, " + 
				"e.firstName, e.lastName, e.image FROM leave_application l, employee e where status= 1 and l.empId=e.empId; "; 
		List<Map<String,String>> pendingRequests = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    String url="jdbc:mysql://localhost:3306/hrms";
		    com = DriverManager.getConnection(url,"root","123456789");
		   // com = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hrms?user=root");
		    PreparedStatement stmt = com.prepareStatement(selectRequestQuery);
		    ResultSet rs = stmt.executeQuery();
		    pendingRequests = new ArrayList<Map<String, String>>();
		    while (rs.next()) {
			System.out.println(rs.getString("empId") + " , " + rs.getString("leaveType") + " , " + rs.getString("reason") + " ," + rs.getString("noOfDays"));
			Map<String, String> row = new HashMap<String, String>();
			row.put("applicationId", rs.getString("applicationId"));
			row.put("empId", rs.getString("empId"));
			row.put("leaveType", rs.getString("leaveType"));
			row.put("reason", rs.getString("reason"));
			row.put("noOfDays", rs.getString("noOfDays"));
			row.put("firstName", rs.getString("firstName"));
			row.put("lastName", rs.getString("lastName"));
			row.put("image", rs.getString("image"));
			
			pendingRequests.add(row);
		}
		   com.close();
		}catch (ClassNotFoundException e)	{
			e.printStackTrace();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				com.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return  pendingRequests;
		
	}
		public boolean acceptingLeave(Long applicationId) {
			Connection com = null;
	   boolean successfull= false;
		//String selectRequestQuery = "SELECT * FROM leave_application where status= 1"; 
		//List<Map<String,String>> pendingRequests = null;
	   
		try {
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat2.setTimeZone(TimeZone.getTimeZone("GMT"));
			java.sql.Date timeNow = new java.sql.Date(new Date().getTime());
		Class.forName("com.mysql.cj.jdbc.Driver");
	    String url="jdbc:mysql://localhost:3306/hrms";
		    com = DriverManager.getConnection(url,"root","123456789");
		    //com = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hrms?user=root");
		    String updateQuery = "UPDATE leave_application SET  reviewedDate=?,  reviewedBy='aleena',status=2 WHERE applicationId=? ";  
		    PreparedStatement stmt = com.prepareStatement(updateQuery);
		    stmt.setDate(1,timeNow );
		    stmt.setLong(2,applicationId);
		    boolean updated = stmt.execute();
		    //ResultSet rs = stmt.executeQuery();
		    System.out.println(updated);
	
		   com.close();
		}catch (ClassNotFoundException e)	{
				e.printStackTrace();

			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
		try {
				com.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return   successfull;
		
	}
		public boolean rejectingLeave(Long applicationId) {
			Connection com = null;
	   boolean successfull= false;
		//String selectRequestQuery = "SELECT * FROM leave_application where status= 1"; 
		//List<Map<String,String>> pendingRequests = null;
	   
		try {
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat2.setTimeZone(TimeZone.getTimeZone("GMT"));
			java.sql.Date timeNow = new java.sql.Date(new Date().getTime());
		Class.forName("com.mysql.cj.jdbc.Driver");
	    String url="jdbc:mysql://localhost:3306/hrms";
		    com = DriverManager.getConnection(url,"root","123456789");
		    //com = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hrms?user=root");
		    String updateQuery = "UPDATE leave_application SET  reviewedDate=?,  reviewedBy='rahul',status=3 WHERE applicationId=? ";  
		    PreparedStatement stmt = com.prepareStatement(updateQuery);
		    stmt.setDate(1,timeNow );
		    stmt.setLong(2,applicationId);
		    boolean updated = stmt.execute();
		    //ResultSet rs = stmt.executeQuery();
		    System.out.println(updated);
	
		   com.close();
		}catch (ClassNotFoundException e)	{
				e.printStackTrace();

			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
		try {
				com.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return   successfull;
		
	}
		public List<Map<String, String>> getAllApplicationsByAdmin() {
			System.out.println("jdchbsdvj");
			Connection con = null;
			List<Map<String, String>> leavesummary = null;
			System.out.println("after List<Map<String, String>");
			System.out.println(" before try");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/hrms";
				con = DriverManager.getConnection(url, "root", "123456789");
				System.out.println("inside database");
				String leaveapplication = "SELECT l.applicationId,l.empId,l.leaveType,l.reason,l.appliedDate,l.reviewedDate,l.date,l.noOfDays,l.status,e.firstName,e.lastName,e.image,e.gender FROM leave_application l,employee e WHERE l.empId=e.empId;";
				PreparedStatement stmt = con.prepareStatement(leaveapplication);
				ResultSet rs = stmt.executeQuery();
				leavesummary = new ArrayList<Map<String,String>>();
				System.out.println("before while");
				while (rs.next()) {
					Map<String, String> row = new HashMap<String, String>();
					row.put("empId", rs.getString("empId"));
					row.put("leaveType", rs.getString("leaveType"));
					row.put("status", rs.getString("status"));
					row.put("reason", rs.getString("reason"));
					row.put("date", rs.getString("date"));
					row.put("noOfDays", rs.getString("noOfDays"));
					row.put("applicationId", rs.getString("applicationId"));
					row.put("firstName", rs.getString("firstName"));
					row.put("lastName", rs.getString("lastName"));
					row.put("image", rs.getString("image"));
					leavesummary.add(row);
				}
				System.out.println("after while" + leavesummary.size());
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
			return leavesummary;
		}
}
