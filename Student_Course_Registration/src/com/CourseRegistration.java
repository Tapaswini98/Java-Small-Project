package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CourseRegistration
 */
public class CourseRegistration extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out= response.getWriter();
		response.setContentType("text/html");
		String student_name =request.getParameter("studentname");
		int course_id =Integer.parseInt(request.getParameter("courseid"));
		String course_name =request.getParameter("coursename");
		System.out.println(course_name);
		String college_name =request.getParameter("college");
        double duration = Double.parseDouble(request.getParameter("duration"));
		long  price =Long.parseLong(request.getParameter("price"));

		
		
		ServletConfig config =getServletConfig();
		ServletContext context =getServletContext();
		
		String driver = context.getInitParameter("driver");
		String url =context.getInitParameter("url");
		
		String username = config.getInitParameter("username");
		String password =config.getInitParameter("password");
		int status =0;
		try 
		{
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,username, password);
			String sql= "insert into student values(?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, student_name);
			pst.setInt(2, course_id);
			pst.setString(3, course_name);
			pst.setString(4, college_name);
			pst.setDouble(5, duration);
			pst.setLong(6, price);
			status= pst.executeUpdate();
			
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		if(status>0)
		
			out.println("Registration Success");
			
		else
			out.println("Error in Registration");
	}

}
