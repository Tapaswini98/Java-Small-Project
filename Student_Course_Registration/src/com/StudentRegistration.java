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

public class StudentRegistration extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out =response.getWriter();
		response.setContentType("text/html");
		
		int roll =Integer.parseInt(request.getParameter("roll"));
		String name =request.getParameter("name");
		double cgpa = Double.parseDouble(request.getParameter("cgpa"));
		long  phone =Long.parseLong(request.getParameter("phone"));
		String address=request.getParameter("address");
		
		
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
			String sql= "insert into student values(?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, roll);
			pst.setString(2, name);
			pst.setDouble(3, cgpa);
			pst.setLong(4, phone);
			pst.setString(5, address);
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
