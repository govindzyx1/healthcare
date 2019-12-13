//package com;

import javax.servlet.*;
import java.sql.*;
import java.io.*;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connection.ConnectionFactory;



public class UserLogin extends HttpServlet 
	{
	private static final long serialVersionUID = 1L;
    RequestDispatcher rd;
	Connection con=null;
	Statement  st=null; 
	ResultSet rs=null;
	PreparedStatement ps;
	PrintWriter pw;
	
    public void init(ServletConfig config) throws ServletException 
		{
		
			
	}

	
	public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
		{

           
		    pw=response.getWriter();
		 Connection con=ConnectionFactory.createConnection();
		 pw.println(con);
		 String uname=request.getParameter("name");
		 
		 String password=request.getParameter("password");

		

		 String s1=request.getParameter("Submit");
		if(s1.equals("LOGIN"))
			{
		String query="Select USER_ID,Uname,pwd from USER_INFO where Uname='"+uname+"' and pwd='"+password+"'";
		
		  try
			   {
			   
				ps=con.prepareStatement(query);
                rs=ps.executeQuery();

                 if(rs.next())
					   {
					

                    String userID=Integer.toString(rs.getInt("USER_ID"));
					
                    String usname=rs.getString("UNAME");
					
                   

					HttpSession session=request.getSession(true);
                    session.setAttribute("userID",userID);
					session.setAttribute("usname",usname);


					 rd=request.getRequestDispatcher("./userhome.html");
			         rd.forward(request,response);
					    }	



				
				    else{
				   rd=request.getRequestDispatcher("Error1.jsp");
			       rd.forward(request,response);
				  
				   }
				   
			}
			catch(Exception sqle)
				{
				pw.println(sqle);
			}
		
			}
			}

			public void destroy()
		     {
		    try
		       {
			con.close();
			    }
		 catch(Exception e)
			{
			
		}
	}


}
