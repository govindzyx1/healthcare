

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import connection.ConnectionFactory;



public class UpdateSymptom extends HttpServlet {
	
	Connection con=null;   
	PreparedStatement ps=null;
	ResultSet rs=null;
	public void init()
	{
	
		

	}

		public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
			{
    
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 int id=Integer.parseInt(request.getParameter("symptomId"));
		 
		String name=request.getParameter("symptomname");
		
		
		
		
		
		
	
		try
        {
     Connection con=ConnectionFactory.createConnection();           	
        
		  
         

			ps=con.prepareStatement("update SYMPTOMS set SID=?,SYMPTOMNAME=? where SID="+id);
			
			
		    ps.setInt(1,id);
		    ps.setString(2,name);
           
		    
		   
			int i=ps.executeUpdate();
			

		
		RequestDispatcher rd=request.getRequestDispatcher("/seccessUpdate.jsp");

		rd.forward(request,response);
		}
		catch(Exception ea)
		{
			out.println(ea);		
		}
		}
			


}

